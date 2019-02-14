package Managers;
import java.io.File;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import Builders.XMLBuilder;
import Enums.ArmorType;
import Enums.ArmorType.Type;
import Enums.Grade;
import Enums.ItemType;
import Enums.Material;
import Enums.WeaponType;
import Main.Main;
import Util.StatsSet;


public class XMLManager
{
	private String _inFolder = Main.FOLDER + "input_xml/";
	private String _outFolder = Main.FOLDER + "output_xml/";
	
	private Map<Integer, StatsSet> _allItems = new LinkedHashMap<>();
	
	private Map<Integer, List<String>> _comments = new HashMap<>();
	private List<String> _commentBuffer = new LinkedList<>();
	
	private int _weaponsLoaded = 0;
	private int _armorsLoaded = 0;
	private int _jewelsLoaded = 0;
	
	public Map<Integer, StatsSet> getAllItems()
	{
		return _allItems;
	}
	
	public void load()
	{
		File inFolderFile = new File(_inFolder);
		File outFolderFile = new File(_outFolder);
			
		if (!inFolderFile.exists())
			inFolderFile.mkdir();
		
		if (!outFolderFile.exists())
			outFolderFile.mkdirs();
			
		if (!inFolderFile.isDirectory() || inFolderFile.listFiles().length == 0)
		{
			Main.log(this, "Папка с входящими данными - " + inFolderFile.getAbsolutePath() + " пуста!");
			return;
		}
		
		for (File f : inFolderFile.listFiles())
		{
			parseItem(f);
		}
		
		sort();
		buildItemList();
		
		Main.log(this, "Загружено:");
		Main.log(this, "Оружия: " + _weaponsLoaded);
		Main.log(this, "Брони: " + _armorsLoaded);
		Main.log(this, "Бижутерии: " + _jewelsLoaded);
		Main.log(this, "Других итемов: " + (_allItems.size() - _weaponsLoaded - _armorsLoaded - _jewelsLoaded));
		Main.log(this, "Всего: " + _allItems.size());
	}
	
	public void buildItemList()
	{
		int startId = (int) _allItems.keySet().toArray()[0];
		
		PrintStream ps = changePrintStream(null, startId / 100 * 100); // им файла начинается с 0
		
		int itemId;
		List<String> item;
		
		for (StatsSet newItem : _allItems.values())
		{
			itemId = newItem.getInteger("id");
			
			if (itemId > 0 && itemId % 100 == 0 )
				ps = changePrintStream(ps, itemId);
			
			// Если есть комментарии к итему - запишем
			if (_comments.containsKey(itemId))
			{
				for (String s : _comments.get(itemId))
				{
					ps.println(s);
				}
			}
			
			try
			{
				item = XMLBuilder.buildItem(newItem);
				
				for (String str : item)
				{
					ps.println(str);
				}
			}
			catch (Exception e)
			{
				Main.log(this, "Ошибка при сборе итема с ID - " + itemId + ": " + e.toString());
				continue;
			}
		}
		
		ps.close();
	}
	
	private void parseItem(File setDoc)
	{
		Scanner sc = null;
		
		try
		{
			sc = new Scanner(setDoc);
			
			Map<Integer, String> _lines = new LinkedHashMap<Integer, String>();
			
			int i = 1;
			while (sc.hasNextLine())
			{
				_lines.put(i, sc.nextLine());
				i++;
			}
			
			int itemId = 0;
			String iType;
			ItemType itemType = null;
			String[] temp;
			
			for (String line : _lines.values())
			{
				// Парсим комментарий
				if (line.startsWith("/"))
				{
					if (line.startsWith("//"))
						parseComment(line);
					
					continue;
				}
				
				temp = line.split("\t");
				int newId = Integer.parseInt(temp[0]);
				
				if (!_commentBuffer.isEmpty())
				{
					if (!_comments.containsKey(newId))
						_comments.put(newId, new LinkedList<>(_commentBuffer));
					else
						Main.log(this, "Внимание! Попытка записи комментария на уже существующие!: " + newId);
					
					_commentBuffer.clear();
				}
				
				if (itemId != 0 && newId - itemId != 1)
					Main.log(this, "Внимание! Id идет не по порядку: " + newId);
				
				itemId = newId;
				
				try
				{
					iType = temp[2];
					
					if (iType == null || iType.equals(""))
					{
						Main.log(this, "Ошибка! Не указан тип итема. ID - " + itemId);
						continue;
					}
					
					if (WeaponType.contains(iType))
						itemType = ItemType.Weapon;
					else if (ArmorType.contains(iType))
						itemType = ItemType.Armor;
					else
						itemType = ItemType.EtcItem;
					
					StatsSet item = new StatsSet();
					
					item.set("id", itemId);
					item.set("type", itemType);
					item.set("name", temp[1]);
					
					if (itemType == ItemType.Weapon)
					{
						WeaponType wType = WeaponType.valueOf(iType);
						
						item.set("icon", temp[3]);
						item.set("default_action", "equip");
						item.set("weapon_type", wType);
						item.set("grade", Grade.valueOf(temp[4]));
						item.set("material", wType.getMaterial());
						item.set("weight", wType.getWeight());
						item.set("price", temp[5]);
						item.set("pAtk", temp[6]);
						item.set("mAtk", temp[7]);
						
						if (temp.length > 8)
							item.set("old_id", Integer.parseInt(temp[8]));
						
						_weaponsLoaded++;
					}
					else if (itemType == ItemType.Armor)
					{
						ArmorType aType = ArmorType.valueOf(iType);
						Type aWeight = null;
						
						int offset = 0;
						
						if (Type.contains(temp[3]))
						{
							aWeight = Type.valueOf(temp[3]);
							
							item.set("armor_type", aWeight);
							
							offset++;
						}
						
						item.set("bodypart", aType);
						item.set("material", aType.getMaterial(aWeight));
						item.set("default_action", "equip");
						item.set("icon", temp[3 + offset]);
						item.set("grade", Grade.valueOf(temp[4 + offset]));
						item.set("weight", aType.getWeight(aWeight));
						item.set("price", temp[5 + offset]);
						item.set("pDef", temp[6 + offset]);
						item.set("mDef", temp[7 + offset]);
						
						if (temp.length > 8 + offset)
							item.set("old_id", Integer.parseInt(temp[8 + offset]));
						
						if (aType == ArmorType.neck || aType == ArmorType.earring || aType == ArmorType.ring)
							_jewelsLoaded++;
						else
							_armorsLoaded++;
					}
					else
					{
						item.set("material", Material.cloth);
						item.set("weight", "999");
					}
					
					_allItems.put(itemId, item);
				}
				catch (Exception e)
				{
					Main.log(this, "Ошибка при чтении итема с ID - " + itemId + ": " + e.toString());
					continue;
				}
			}
		}
		catch (Exception e)
		{
		}
		finally
		{
			sc.close();
		}
	}
	
	private void parseComment(String line)
	{
		try
		{
			String tmpComment = line.substring(2).trim();
			String comment = "	<!--";
			int charsCount = 0;
			
			for (char c : tmpComment.toCharArray())
			{
				if (c != '=')
				{
					comment = comment.concat(String.valueOf(c));
					charsCount++;
				}
			}
			
			comment = comment.concat("-->");
			
			if (charsCount > 0)
				_commentBuffer.add(comment);
		}
		catch (Exception e)
		{
			Main.log(this, "Ошибка чтения комментария: " + line + ": " + e.getMessage());
		}
	}
	
	private PrintStream changePrintStream(PrintStream ps, int startId)
	{
		if (ps != null)
		{
			ps.print("</list>");
			ps.close();
		}
		
		File items = null;
		
		try
		{
			String part1 = String.valueOf(startId);
			if (startId == 0)
				part1 = ("0" + startId);
			String part2 = String.valueOf(startId + 100);
			
			for (int i = part1.length(); i < 5; i++)
			{
				part1 = 0 + part1;
				
				if (part2.length() < part1.length())
					part2 = 0 + part2;
			}
			
			items = new File(_outFolder + part1 + "-" + part2 +".xml");
			
			if (!items.exists())
				items.createNewFile();
			
			ps = new PrintStream(items);
			ps.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			ps.println("<list>");
		}
		catch (Exception e)
		{
			Main.log(this, "Ошибка при инициализации нового потока вывода: " + e.getMessage());
		}
		
		return ps;
	}
	
	private void sort()
	{
		List<Map.Entry<Integer, StatsSet>> list = new LinkedList<>(_allItems.entrySet());
	    
		Collections.sort(list, new Comparator<Map.Entry<Integer, StatsSet>>()
		{
			@Override
			public int compare(Entry<Integer, StatsSet> arg0, Entry<Integer, StatsSet> arg1)
			{
				return arg0.getKey().compareTo(arg1.getKey());
			} 
		});
		
		Map<Integer, StatsSet> result = new LinkedHashMap<>();
		for (Map.Entry<Integer, StatsSet> entry : list)
		{
			result.put(entry.getKey(), entry.getValue());
		}
		
		_allItems = result;
	}
	
	private static class SingletonHolder
	{
		protected static final XMLManager _instance = new XMLManager();
	}
	
	public static final XMLManager getInstance()
	{
		return SingletonHolder._instance;
	}
}