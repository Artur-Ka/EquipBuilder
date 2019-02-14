package Enums.Structure;

/**
 * Структура WeaponGrp.dat CT 2.5
 * 
 * @descr
 * В знаковом (signed) итегере для отрицания -1, в беззнаковом (unsigned) - 0.<BR>
 * 
 * Пример: CRYSTALLIZABLE - без знака, значит, если оружие нельзя разбить - ставить 0. 
 * Однако, HERO - знаковый, значит, если оружие не геройское, ставим -1.<BR>
 * 
 * 
 * @author Hei
 */
public class WeaponGrp
{
	/** Таг <BR><BR>(UNSIGNED INT_32)<BR> Для всего оружия 0 */
	public static final int TAG = 0;
	/** ID оружия <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int ID = 1;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int DROP_TYPE = 2;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int DROP_ANIMATION_TYPE = 3;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int DROP_RADIUS = 4;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int DROP_HEIGHT = 5;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int UNK_0 = 6;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_MESH_1 = 7;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_MESH_2 = 8;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_MESH_3 = 9;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_TEX_1 = 10;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_TEX_2 = 11;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_TEX_3 = 12;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_EXTRATEX_1 = 13;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_1 = 14;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_2 = 15;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_3 = 16;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_4 = 17;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_5 = 18;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_6 = 19;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_7 = 20;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int NEW_DATA_8 = 21;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int ICON_1 = 22;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int ICON_2 = 23;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int ICON_3 = 24;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int ICON_4 = 25;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int ICON_5 = 26;
	/** Срок службы <BR><BR>(INT_32)<BR> Неограниченно = -1 */
	public static final int DURABILITY = 27;
	/** Вес <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int WEIGHT = 28;
	/** Материал <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int MATERIAL = 29;
	/** Кристализуется? <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int CRYSTALLIZABLE = 30;
	/** <BR><BR>(HEX)<BR> */
	public static final int UNK_1 = 31;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int UNK_2_COUNT = 32;
	/** Массив, длина которого равна UNK_2_COUNT. <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int UNK_2_TAB = 33;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int UNK_3 = 34;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int TIMETAB = 35;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int BODY_PART = 36;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int HANDNESS = 37;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int MESH_COUNT = 38;
	/** Массив, длина = MESH_COUNT <BR><BR>(UNICODE)<BR> */
	public static final int MESH = 39;
	/** Массив, длина = MESH_COUNT <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int UNKVAL = 41;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int TEX_COUNT = 43;
	/** Массив, длина = TEX_COUNT <BR><BR>(UNICODE)<BR> */
	public static final int TEX = 44;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int ITEM_SOUND_COUNT = 48;
	/** Массив, длина = ITEM_SOUND_COUNT <BR><BR>(UNICODE)<BR> */
	public static final int ITEM_SOUND = 49;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int DROP_SOUND = 53;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int EQUIP_SOUND = 54;
	/** <BR><BR>(UNICODE)<BR> */
	public static final int EFFECT = 55;
	
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int RND_DMG = 56;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int P_ATK = 57;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int M_ATK = 58;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int TYPE = 59;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int GRADE = 60;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int CRIT = 61;
	/** <BR><BR>(INT_32)<BR> */
	public static final int HIT_MOD = 62;
	/** Ловкость <BR><BR>(INT_32)<BR> */
	public static final int SHLD_EVAS = 63;
	/** Защита щита <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int SHLD_DEF = 64;
	/** Шанс блока щитом <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int SHLD_RATE = 65;
	/** Скорость атаки <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int ATK_SPD = 66;
	/** <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int MP_CONSUME = 67;
	/** Потребление МП <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int SS_CONSUME = 68;
	/** Потребление физ. сосок <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int SPS_CONSUME = 69;
	/** Потребление маг. сосок <BR><BR>(UNSIGNED INT_32)<BR> */
	public static final int CURVATURE = 70;
	/** Геройское ли оружие (аура?) <BR><BR>(INT_32)<BR> */
	public static final int IS_HERO = 72;
}
