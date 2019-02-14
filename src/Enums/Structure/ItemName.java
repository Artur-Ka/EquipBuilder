package Enums.Structure;

public class ItemName
{
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int ID = 0;
	
	/** <BR><BR>(UNICODE)<BR> */
	public static final int NAME = 1;
	
	/** 2 - Дополнение к названию (жёлтым цветом) <BR><BR>(UNICODE)<BR> */
	public static final int ADD_NAME = 2;
	
	/** 3 - Описание (ниже названия, белым цветом) <BR><BR>(ASCF)<BR> */
	public static final int DESCRIPTION = 3;
	
	/** 4 - хз на что влияет. Ставить -1 <BR><BR>(INT_32)<BR> */
	public static final int POPUP = 4;
	
	/** 5 - Количество супер (?) частей к сету. Похоже, просто ставим == COUNT <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int SUPER_COUNT = 5;
	
	/** 6 - Количество частей к сету. <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int COUNT = 6;
	
	/** Само тело в описании частей к сету. 8 - поножи и т. д. <BR><BR>(UNICODE)<BR> */
	public static final int ARMOR_PART = 7;
	
	/** 12 - Описание (почти в самом низу, бледно-жёлтым цветом) <BR><BR>(ASCF)<BR> */
	public static final int BONUS_DESCRIPTION = 12;
	
	/** 5 - Количество супер (?) частей к сету. Похоже, просто ставим == EXTRA_COUNT <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int EXTRA_SUPER_COUNT = 13;
	
	/** 6 - Количество дополнительных частей к сету. <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int EXTRA_COUNT = 14;
	
	/** Дополнительные части сета (Использовалось для щитов) <BR><BR>(UNICODE)<BR> */
	public static final int EXTRA_ARMOR_PART = 15;
	
	/** 16 - Описание (почти в самом низу, бледно-жёлтым цветом) <BR><BR>(ASCF)<BR> */
	public static final int EXTRA_BONUS_DESCRIPTION = 16;
	
	/** 26 - Уровень заточки (в самом низу, тёмным цветом) <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int ENCHANT_AMOUNT = 26;
	
	/** 27 - Описание (в самом низу, тёмным цветом) <BR><BR>(ASCF)<BR> */
	public static final int ENCHANT_DESCRIPTION = 27;
	
	/** 28 - Влияет на цвет названия вещи. (0 - серый, 1 - белый, 2 - жёлтый). <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int UNK_2 = 28;
}
