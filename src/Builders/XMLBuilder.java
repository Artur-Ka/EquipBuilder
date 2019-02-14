package Builders;

import java.util.ArrayList;
import java.util.List;

import Enums.ArmorType;
import Enums.Grade;
import Enums.ArmorType.Type;
import Enums.ItemType;
import Enums.Material;
import Enums.WeaponType;
import Util.StatsSet;

public class XMLBuilder
{
	public static List<String> buildItem(StatsSet set) throws Exception
	{
		List<String> item = new ArrayList<>();
		
		String itemId = set.getString("id");
		ItemType itemType = set.getEnum("type", ItemType.class);
		String itemName = set.getString("name");
		String icon = set.getString("icon");
		String defaultAction = set.getString("default_action", null);
		Grade grade = set.getEnum("grade", Grade.class, null);
		Material material = set.getEnum("material", Material.class);
		String weight = set.getString("weight");
		String price = set.getString("price");
		boolean isEnchantEnabled = set.getBool("enchant_enabled", false);
		String enchant4Skill = set.getString("enchant4_skill", null);
		int pAtk = set.getInteger("pAtk", -1);
		int mAtk = set.getInteger("mAtk", -1);
		int pDef = set.getInteger("pDef", -1);
		int mDef = set.getInteger("mDef", -1);
		
		WeaponType wType = set.getEnum("weapon_type", WeaponType.class, null);
		ArmorType aType = set.getEnum("bodypart", ArmorType.class, null);
		Type aWeight = set.getEnum("armor_type", Type.class, null); // Тип брони: Хеви, Лайт, Роба
		
		
		append(item, "	<item id=\"", itemId, "\" type=\"", itemType.toString(), "\" name=\"", itemName, "\">");
		append(item, "		<set name=\"icon\" val=\"", icon, "\"/>");
		
		if (defaultAction != null)
			append(item, "		<set name=\"default_action\" val=\"", defaultAction, "\"/>");
		
		if (wType != null)
		{
			append(item, "		<set name=\"weapon_type\" val=\"", wType.toString(), "\"/>");
			append(item, "		<set name=\"bodypart\" val=\"", wType.getBodypart(), "\"/>");
		}
		else if (aType != null)
		{
			if (aWeight != null)
				append(item, "		<set name=\"armor_type\" val=\"", aWeight.toString(), "\"/>");
			
			append(item, "		<set name=\"bodypart\" val=\"", aType.getBodypart(), "\"/>");
		}
		
		if (grade != null)
			append(item, "		<set name=\"grade\" val=\"", grade.toString(), "\"/>");
		
		if (wType != null)
		{
			append(item, "		<set name=\"random_damage\" val=\"", wType.getRandomDamage(), "\"/>");
			append(item, "		<set name=\"attack_range\" val=\"", wType.getAttackRange(), "\"/>");
			append(item, "		<set name=\"damage_range\" val=\"", wType.getDamageRange(), "\"/>");
		}
		
		append(item, "		<set name=\"material\" val=\"", material.toString(), "\"/>");
		append(item, "		<set name=\"weight\" val=\"", weight, "\"/>");
		append(item, "		<set name=\"price\" val=\"", price, "\"/>");
		
		if (isEnchantEnabled)
			append(item, "		<set name=\"enchant_enabled\" val=\"1\"/>");
		
		if (enchant4Skill != null)
			append(item, "		<set name=\"enchant4_skill\" val=\"", enchant4Skill, "\"/>");
		
		if (wType != null && wType.getRaces() != null)
		{
			append(item, "		<cond msgId=\"1518\">");
			append(item, "			<player races=\"" + wType.getRaces() + "\" />");
			append(item, "		</cond>");
		}
		
		if (wType != null && (pAtk > 0 || mAtk > 0))
		{
//			if (wType == WeaponType.staff || wType == WeaponType.rod)
//				append(item, "		<set name=\"is_magic_weapon\" val=\"true\"/>");
			
			append(item, "		<for>");
			append(item, "			<set order=\"0x08\" stat=\"pAtk\" val=\"", String.valueOf(pAtk), "\"/>");
			append(item, "			<set order=\"0x08\" stat=\"mAtk\" val=\"", String.valueOf(mAtk), "\"/>");
			append(item, "			<set order=\"0x08\" stat=\"rCrit\" val=\"", wType.getRCrit(), "\"/>");
			
			if (wType == WeaponType.blunt || wType == WeaponType.big_blunt || wType == WeaponType.dual_blunts || wType == WeaponType.short_bow || wType == WeaponType.crossbow)
				append(item, "			<add order=\"0x10\" stat=\"accCombat\" val=\"4\"/>");
		                          
			append(item, "			<set order=\"0x08\" stat=\"pAtkSpd\" val=\"", wType.getPAtkSpd(), "\"/>");
			
			if (set.getString("enchant_enabled", null) != null)
				append(item, "			<enchant val=\"0\" order=\"0x0C\" stat=\"pAtk\"/>",
		                     "			<enchant val=\"0\" order=\"0x0C\" stat=\"mAtk\"/>");
			
			append(item, "		</for>");
		}
		else if (aType != null && ((pDef > 0 || mDef > 0) || aType == ArmorType.shield))
		{
			append(item, "		<for>");
			
			if (aType == ArmorType.shield)
			{
				append(item, "			<sub order=\"0x10\" stat=\"rEvas\" val=\"8\" />");
				append(item, "			<set order=\"0x08\" stat=\"rShld\" val=\"10\" />");
			}
			
			if (pDef > 0)
				append(item, "			<add order=\"0x10\" stat=\"pDef\" val=\"", String.valueOf(pDef), "\" />");
			
			if (mDef > 0)
				append(item, "			<add order=\"0x10\" stat=\"mDef\" val=\"", String.valueOf(mDef), "\" />");
			
			if (pDef > 0)
				append(item, "			<enchant order=\"0x0C\" stat=\"pDef\" val=\"0\" />");
			
			if (mDef > 0)
				append(item, "			<enchant order=\"0x0C\" stat=\"mDef\" val=\"0\" />");
			
			append(item, "		</for>");
		}
		
		append(item, "	</item>");
			
		return item;
	}
	
	private static final void append(List<String> item, String... strings)
	{
		StringBuilder sb = new StringBuilder();
		
		for (String str : strings)
		{
			sb.append(str);
		}
		
		item.add(sb.toString());
	}
}
