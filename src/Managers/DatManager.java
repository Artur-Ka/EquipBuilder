package Managers;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Builders.DATBuilder;
import Enums.ArmorType;
import Enums.Grade;
import Enums.ArmorType.Type;
import Enums.ItemType;
import Enums.WeaponType;
import Main.Main;
import Util.StatsSet;

public class DatManager
{
	private File _inFolder = new File(Main.FOLDER + "input_dat/");
	private File _outFolder = new File(Main.FOLDER + "output_dat/");
	
	private File _weaponsGrp = new File(_outFolder + "/WeaponGrp.txt");
	private File _armorsGrp = new File(_outFolder + "/ArmorGrp.txt");
	private File _etcItemGrp = new File(_outFolder + "/ArmorGrp.txt");
	private File _itemName = new File(_outFolder + "/ItemName-ru.txt");
	
	private Map<Integer, String> _weapons = new HashMap<>();
	private Map<Integer, String> _armors = new HashMap<>();
	private Map<Integer, String> _etcItems = new HashMap<>();
	
	private Map<Integer, String> _itemNames = new HashMap<>();
	
	
	public Map<Integer, String> getWeapons()
	{
		return _weapons;
	}
	
	public Map<Integer, String> getArmors()
	{
		return _armors;
	}
	
	public Map<Integer, String> getEtcItems()
	{
		return _etcItems;
	}
	
	public Map<Integer, String> getItemNames()
	{
		return _itemNames;
	}
	
	public void load()
	{
		if (!_inFolder.exists())
			_inFolder.mkdirs();
		
		if (!_outFolder.exists())
			_outFolder.mkdirs();
		
		if (!_inFolder.isDirectory() || _inFolder.listFiles().length == 0)
		{
			Main.log(this, "Папка с входящими данными - " + _inFolder.getAbsolutePath() + " пуста!");
			return;
		}
		
		for (File f : _inFolder.listFiles())
		{
			try
			{
				if (f.getName().endsWith("grp.txt") || f.getName().endsWith("Grp.txt"))
					parseGrp(f);
				if (f.getName().endsWith("name-ru.txt") || f.getName().endsWith("Name-ru.txt"))
					parseName(f);
			}
			catch (Exception e)
			{
				Main.log(this, "Ошибка при чтении файла: " + f.getName());
			}
		}
		
		Main.log(this, "Загружено оружия: " + _weapons.size());
		Main.log(this, "Загружено брони: " + _armors.size());
		Main.log(this, "Загружено других итемов: " + _etcItems.size());
		
		buildNameAndGrp();
	}
	
	private void parseGrp(File itemGrp)
	{
		Scanner sc = null;
		
		try
		{
			sc = new Scanner(itemGrp);
		}
		catch (Exception e)
		{
		}
		
		if (sc == null)
			return;
		
		String line;
		
		while (sc.hasNextLine())
		{
			line = sc.nextLine();
			
			if (line.startsWith("0	") && itemGrp.getName().equalsIgnoreCase("weapongrp.txt"))
				_weapons.put(Integer.parseInt(line.split("\t")[1]), line);
			
			else if (line.startsWith("1	") && itemGrp.getName().equalsIgnoreCase("armorgrp.txt"))
				_armors.put(Integer.parseInt(line.split("\t")[1]), line);
			
			else if (line.startsWith("2	") && itemGrp.getName().equalsIgnoreCase("etcitemgrp.txt"))
				_etcItems.put(Integer.parseInt(line.split("\t")[1]), line);
		}
	}
	
	private void parseName(File itemNames)
	{
		Scanner sc = null;
		
		try
		{
			sc = new Scanner(itemNames);
		}
		catch (Exception e)
		{
		}
		
		if (sc == null)
			return;
		
		String line;
		int itemId;
		
		while (sc.hasNextLine())
		{
			line = sc.nextLine();
			
			try
			{
				itemId = Integer.parseInt(line.split("\t")[0]);
				_itemNames.put(itemId, line);
			}
			catch (Exception e)
			{
				continue;
			}
		}
	}
	
	private void buildNameAndGrp()
	{
		PrintStream weaponsGrp = null;
		PrintStream armorsGrp = null;
		PrintStream etcItemGrp = null;
		PrintStream itemName = null;
		
		PrintStream currentGrp = null;
		
		try
		{
			if (!_weaponsGrp.exists())
				_weaponsGrp.createNewFile();
			
			if (!_armorsGrp.exists())
				_armorsGrp.createNewFile();
			
			if (!_etcItemGrp.exists())
				_etcItemGrp.createNewFile();
			
			if (!_itemName.exists())
				_itemName.createNewFile();
			
			weaponsGrp = new PrintStream(_weaponsGrp);
			armorsGrp = new PrintStream(_armorsGrp);
			etcItemGrp = new PrintStream(_etcItemGrp);
			itemName = new PrintStream(_itemName);
		}
		catch (Exception e)
		{
			Main.log(this, "Ошибка при инициализации нового потока вывода: " + e.getLocalizedMessage());
			return;
		}
		
		String lineGrp = null;
		String lineName = null;
		
		// Считает вещи одного грейда
		int[] wGradePrice = new int[WeaponType.values().length];
		int[] aGradePrice = new int[ArmorType.values().length];
		int[][] wGradeCounter = new int[WeaponType.values().length][Grade.values().length];
		int[][] aGradeCounter = new int[ArmorType.values().length][Grade.values().length];
		
		for (StatsSet item : XMLManager.getInstance().getAllItems().values())
		{
			int itemId = item.getInteger("id");
			ItemType itemType = item.getEnum("type", ItemType.class);
			int itemOldId = item.getInteger("old_id", -1);
			Grade grade = item.getEnum("grade", Grade.class, null);
			
			if (itemOldId < 1)
			{
				Main.log(this, "Внимание! Не указан старый ID для билда DAT! ID - " + itemId + " (" + itemType.toString() + ")");
				continue;
			}
			
			lineGrp = findItemDescr(itemOldId);
			lineName = findItemName(itemOldId);
			
			if (lineGrp == null)
			{
				Main.log(this, "Не найдены параметры итема - " + itemOldId);
				continue;
			}
			
			String name = item.getString("name");
			WeaponType wType = item.getEnum("weapon_type", WeaponType.class, null);
			ArmorType aType = item.getEnum("bodypart", ArmorType.class, null);
			Type aWeight = item.getEnum("armor_type", Type.class, null);
			int price = Integer.parseInt(item.getString("price", "0"));
			
			try
			{
				switch (itemType)
				{
					case Weapon:
						if (wType == null)
						{
							Main.log(this, "Ошибка! Не указан тип оружия. ID - " + itemId);
							continue;
						}
						
						currentGrp = weaponsGrp;
						lineGrp = DATBuilder.buildWeapon(itemId, wType, item, lineGrp);
						
						if (wGradePrice[wType.ordinal()] < price)
						{
							wGradePrice[wType.ordinal()] = price;
							wGradeCounter[wType.ordinal()][grade.ordinal()]++;
						}
						
						break;
					
					case Armor:
						if (aType == null)
						{
							Main.log(this, "Ошибка! Не указан тип брони. ID - " + itemId);
							continue;
						}
						
						if (aGradePrice[aType.ordinal()] < price)
						{
							aGradePrice[aType.ordinal()] = price;
							aGradeCounter[aType.ordinal()][grade.ordinal()]++;
						}
						
						if (aType == ArmorType.shield)
						{
							currentGrp = weaponsGrp;
							lineGrp = DATBuilder.buildWeapon(itemId, null, item, lineGrp);
						}
						else
						{
							currentGrp = armorsGrp;
							lineGrp = DATBuilder.buildArmor(itemId, aType, aWeight, item, lineGrp);
						}
						
						break;
						
					case EtcItem:
						
						currentGrp = etcItemGrp;
						
						break;
				}
			}
			catch (Exception e)
			{
				Main.log(this, "Ошибка генерирования описания для итема с ID - " + itemId + ":- не подходит строка от итема с ID - " + itemOldId + ": ");
				e.printStackTrace();
				continue;
			}
			
			currentGrp.println(lineGrp);
			
			if (lineName != null)
			{
				int wQuality = wType != null ? wGradeCounter[wType.ordinal()][grade.ordinal()] : 0;
				int aQuality = aType != null ? aGradeCounter[aType.ordinal()][grade.ordinal()] : 0;
				
				itemName.println(DATBuilder.buildItemName(itemId, name, wType, aType, aWeight, wQuality, aQuality, lineName));
			}
			else
				Main.log(this, "Не найдено описание для итема - " + itemOldId);
		}
		
		weaponsGrp.close();
		armorsGrp.close();
		etcItemGrp.close();
		itemName.close();
	}
	
	private String findItemDescr(int itemId)
	{
		if (_weapons.containsKey(itemId))
			return _weapons.get(itemId);
		
		else if (_armors.containsKey(itemId))
			return _armors.get(itemId);
		
		else if (_etcItems.containsKey(itemId))
			return _etcItems.get(itemId);
		
		return null;
	}
	
	private String findItemName(int itemId)
	{
		return getItemNames().get(itemId);
	}
	
	private static class SingletonHolder
	{
		protected static final DatManager _instance = new DatManager();
	}
	
	public static final DatManager getInstance()
	{
		return SingletonHolder._instance;
	}
}
