package Builders;

import Enums.ArmorType;
import Enums.ArmorType.Type;
import Enums.Grade;
import Enums.Material;
import Enums.WeaponType;
import Enums.Structure.ArmorGrp;
import Enums.Structure.ItemName;
import Enums.Structure.WeaponGrp;
import Util.StatsSet;

public class DATBuilder
{
	public static String buildWeapon(int id, WeaponType wType, StatsSet item, String oldItem)
	{
		String[] sublines = oldItem.split("\t");
		
		sublines[WeaponGrp.ID] = String.valueOf(id);
		sublines[WeaponGrp.DURABILITY] = "-1";
		sublines[WeaponGrp.WEIGHT] = item.getString("weight");
		sublines[WeaponGrp.MATERIAL] = String.valueOf(item.getEnum("material", Material.class).ordinal());
		sublines[WeaponGrp.CRYSTALLIZABLE] = "0";
		sublines[WeaponGrp.BODY_PART] = (wType != null ? wType.getClientBodypart() : "28");
		sublines[WeaponGrp.HANDNESS] = (wType != null ? wType.getClientHandness() : "0");
		sublines[WeaponGrp.RND_DMG] = (wType != null ? wType.getRandomDamage() : "0");
		sublines[WeaponGrp.P_ATK] = String.valueOf(item.getInteger("pAtk", 0));
		sublines[WeaponGrp.M_ATK] = String.valueOf(item.getInteger("mAtk", 0));
		sublines[WeaponGrp.TYPE] = (wType != null ? wType.getClientType() : "0");
		sublines[WeaponGrp.GRADE] = String.valueOf(item.getEnum("grade", Grade.class).ordinal());
		sublines[WeaponGrp.CRIT] = (wType != null ? wType.getRCrit() : "0");
		sublines[WeaponGrp.HIT_MOD] = (wType != null ? wType.getClientHitMod() : "0");
		sublines[WeaponGrp.SHLD_EVAS] = item.getString("rEvas", "0");
		sublines[WeaponGrp.SHLD_DEF] = item.getString("pDef", "0");
		sublines[WeaponGrp.SHLD_RATE] = item.getString("rShld", "0");
		sublines[WeaponGrp.ATK_SPD] = (wType != null ? wType.getPAtkSpd() : "0");
		sublines[WeaponGrp.MP_CONSUME] = "0";
		sublines[WeaponGrp.SS_CONSUME] = "0";
		sublines[WeaponGrp.SPS_CONSUME] = "0";
		sublines[WeaponGrp.IS_HERO] = "-1";
		
		StringBuilder sb = new StringBuilder();
		
		for (String str : sublines)
		{
			sb.append(str);
			sb.append("\t");
		}
		
		sb.append("\t\t\t");
		
		return sb.toString();
	}
	
	public static String buildArmor(int id, ArmorType aType, Type aWeight, StatsSet item, String oldItem)
	{
		String[] sublines = oldItem.split("\t");
		
		sublines[ArmorGrp.ID] = String.valueOf(id);
		sublines[ArmorGrp.DURABILITY] = "-1";
		sublines[ArmorGrp.WEIGHT] = item.getString("weight");
		sublines[ArmorGrp.MATERIAL] = String.valueOf(item.getEnum("material", Material.class).ordinal());
		sublines[ArmorGrp.CRYSTALLIZABLE] = "0";
		sublines[ArmorGrp.BODY_PART] = (aType != null ? aType.getClientBodypart() : "28");
		sublines[ArmorGrp.TYPE] = (aWeight != null ? String.valueOf(aWeight.ordinal()) : "0");
		sublines[ArmorGrp.GRADE] = String.valueOf(item.getEnum("grade", Grade.class).ordinal());
		sublines[ArmorGrp.AVOID_MOD] = "0";
		sublines[ArmorGrp.P_DEF] = String.valueOf(item.getInteger("pDef", 0));
		sublines[ArmorGrp.M_DEF] = String.valueOf(item.getInteger("mDef", 0));
		sublines[ArmorGrp.MP_BONUS] = "0";
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < sublines.length; i++)
		{
			sb.append(sublines[i]);
			
			if (i < sublines.length - 1)
				sb.append("\t");
		}
		
		return sb.toString();
	}
	
	public static String buildEtcItem()
	{
		return null;
	}
	
	public static String buildItemName(int id, String name, WeaponType wType, ArmorType aType, Type aWeight, int wQuality, int aQuality, String oldName)
	{
		String[] sublines = oldName.split("\t");
		
		sublines[ItemName.ID] = String.valueOf(id);
		sublines[ItemName.NAME] = name;
		sublines[ItemName.ADD_NAME] = "";
		sublines[ItemName.DESCRIPTION] = "u,";
		sublines[ItemName.POPUP] = "-1";
		sublines[ItemName.SUPER_COUNT] = "0";
		sublines[ItemName.COUNT] = "0";
		sublines[ItemName.ARMOR_PART] = "";
		sublines[ItemName.ARMOR_PART + 1] = "";
		sublines[ItemName.ARMOR_PART + 2] = "";
		sublines[ItemName.ARMOR_PART + 3] = "";
		sublines[ItemName.ARMOR_PART + 4] = "";
		sublines[ItemName.BONUS_DESCRIPTION] = "u,";
		sublines[ItemName.EXTRA_SUPER_COUNT] = "0";
		sublines[ItemName.EXTRA_COUNT] = "0";
		sublines[ItemName.EXTRA_BONUS_DESCRIPTION] = "u,";
		sublines[ItemName.EXTRA_ARMOR_PART] = "";
		sublines[ItemName.ENCHANT_AMOUNT] = "0";
		sublines[ItemName.ENCHANT_DESCRIPTION] = "u,";
		sublines[ItemName.UNK_2] = "1";
		
		String description = "u,";
		
		if (wType != null)
		{
			switch (wQuality)
			{
				case 1:
				{
					switch (wType)
					{
						case sword:
						case dagger:
						case big_sword:
						case long_bow:
						case short_bow:
						case staff:
						case rod:
						case ancient_sword:
						case crossbow:
							description = description.concat("Нормальный ");
							break;
						case blunt:
						case big_blunt:
						case lance:
						case rapier:
							description = description.concat("Нормальная ");
							break;
						case fists:
						case dual_swords:
						case dual_blunts:
						case dual_daggers:
							description = description.concat("Нормальные ");
							break;
					}
					break;
				}
				case 2:
				{
					switch (wType)
					{
						case sword:
						case dagger:
						case big_sword:
						case long_bow:
						case short_bow:
						case staff:
						case rod:
						case ancient_sword:
						case crossbow:
							description = description.concat("Хороший ");
							break;
						case blunt:
						case big_blunt:
						case lance:
						case rapier:
							description = description.concat("Хорошая ");
							break;
						case fists:
						case dual_swords:
						case dual_blunts:
						case dual_daggers:
							description = description.concat("Хорошие ");
							break;
					}
					break;
				}
				case 3:
				{
					switch (wType)
					{
						case sword:
						case dagger:
						case big_sword:
						case long_bow:
						case short_bow:
						case staff:
						case rod:
						case ancient_sword:
						case crossbow:
							description = description.concat("Отличный ");
							break;
						case blunt:
						case big_blunt:
						case lance:
						case rapier:
							description = description.concat("Отличная ");
							break;
						case fists:
						case dual_swords:
						case dual_blunts:
						case dual_daggers:
							description = description.concat("Отличные ");
							break;
					}
					break;
				}
			}
			
			description = description.concat(wType.getDescription()).concat(".\\0");
			sublines[ItemName.ENCHANT_DESCRIPTION] = sublines[ItemName.ENCHANT_DESCRIPTION].concat(wType.getClientRaces()).concat("\\0");
		}
		
		if (aType != null)
		{
			switch (aType)
			{
				case chest:
				case full:
				{
					switch (aQuality)
					{
						case 1: description = description.concat("Нормальный "); break;
						case 2: description = description.concat("Хороший "); break;
						case 3:
						case 4: description = description.concat("Отличный "); break;
					}
					
					if (aWeight != null)
						description = description.concat(aWeight.getChestDescr());
					
					description = description.concat(" ").concat(aType.getDescription()).concat(".\\0");
					
					sublines[ItemName.ARMOR_PART] = String.valueOf(id);
					sublines[ItemName.ARMOR_PART + 1] = String.valueOf(id + 1);
					sublines[ItemName.ARMOR_PART + 2] = String.valueOf(id + 2);
					sublines[ItemName.ARMOR_PART + 3] = "";
					sublines[ItemName.ARMOR_PART + 4] = "";
					
					if (aType == ArmorType.chest)
					{
						sublines[ItemName.SUPER_COUNT] = "4";
						sublines[ItemName.COUNT] = "4";
						
						sublines[ItemName.ARMOR_PART + 3] = String.valueOf(id + 3);
					}
					else
					{
						sublines[ItemName.SUPER_COUNT] = "3";
						sublines[ItemName.COUNT] = "3";
					}
					
					break;
				}
				case legs:
				case gloves:
				case boots:
				{
					switch (aQuality)
					{
						case 1: description = description.concat("Нормальные "); break;
						case 2: description = description.concat("Хорошие "); break;
						case 3:
						case 4: description = description.concat("Отличные "); break;
					}
					
					if (aWeight != null)
						description = description.concat(aWeight.getPartsDescr());
					
					description = description.concat(" ").concat(aType.getDescription()).concat(".\\0");
					
					break;
				}
			}
		}
		
		sublines[ItemName.DESCRIPTION] = description;
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < sublines.length; i++)
		{
			sb.append(sublines[i]);
			
			if (i < sublines.length - 1)
				sb.append("\t");
		}
		
		return sb.toString();
	}
}
