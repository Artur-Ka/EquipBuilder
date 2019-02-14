package Util;

import Enums.Structure.ArmorGrp;
import Enums.Structure.EtcItem;
import Enums.Structure.ItemName;
import Enums.Structure.WeaponGrp;

public class Test
{
	public static final String armor_1 = "2	709	0	3	2	5	0	dropitems.drop_sack_m00			dropitemstex.drop_sack_t00				0	0	0	0	0	1	0	0	icon.armor_leather_shirt_i00					-1	400	19	0	5803558	0							1		1	Fighter.MFighter_m001_u		1	MFighter.MFighter_m001_t01_u				ItemSound.itemdrop_sack	ItemSound.itemequip_armor_leather	0	0	0";
	
	
	public static void main(String[] args)
	{
		
		String[] sbs_1 = armor_1.split("\t");
		
		System.out.println(sbs_1[EtcItem.MATERIAL]);
	}
}
