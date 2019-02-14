package Enums;


public enum WeaponType
{
	sword        ("rhand",	"меч",				"10",   "40",   "0;0;40;120",   Material.steel,   "3000",	"9999",   "8",   "360",	"1",	"1",	"Human,Elf,DarkElf", 	"Только у Людей, Эльфов и Тёмных Эльфов есть навыки владения этим оружием."),
	blunt        ("rhand",	"булава",			"20",   "40",   "0;0;40;120",   Material.steel,   "3000",	"9999",   "4",   "360",	"2",	"1",	"Orc,Dwarf", 			"Только у Орков и Гномов есть навыки владения этим оружием."),
	dagger       ("rhand",	"кинжал",			"5",    "40",   "0;0;40;120",   Material.steel,   "1000",	"9999",   "12",  "400",	"3",	"1",	"Human,Elf,DarkElf", 	"Только у Людей, Эльфов и Тёмных Эльфов есть навыки владения этим оружием."),
	big_sword    ("lrhand",	"двуручный меч",	"10",   "40",   "0;0;44;120",   Material.steel,   "6000",	"9999",   "8",   "240",	"1",	"2",	"Human,Elf,DarkElf", 	"Только у Людей, Эльфов и Тёмных Эльфов есть навыки владения этим оружием."),
	big_blunt    ("lrhand",	"двуручная булава",	"20",   "40",   "0;0;44;120",   Material.steel,   "6000",	"9999",   "4",   "240",	"2",	"2",	"Orc,Dwarf", 			"Только у Орков и Гномов есть навыки владения этим оружием."),
	lance        ("lrhand",	"пика",				"10",   "80",   "0;0;66;120",   Material.steel,   "6000",	"9999",   "8",   "240",	"4",	"4",	"Orc,Dwarf", 			"Только у Орков и Гномов есть навыки владения этим оружием."),
	fists        ("lrhand",	"кастеты",			"5",    "40",   "0;0;32;120",   Material.steel,   "3000",	"9999",   "8",   "320",	"5",	"7",	"DarkElf,Orc", 			"Только у Орков и Гномов есть навыки владения этим оружием."),
	dual_swords  ("lrhand",	"парные мечи",		"10",   "40",   "0;0;44;120",   Material.steel,   "5000",	"9999",   "8",   "320",	"8",	"3",	"Human,Elf,DarkElf", 	"Только у Людей, Эльфов и Тёмных Эльфов есть навыки владения этим оружием."),
	dual_blunts  ("lrhand",	"парные булавы",	"20",   "40",   "0;0;44;120",   Material.steel,   "5000",	"9999",   "4",   "320",	"8",	"3",	"Orc,Dwarf", 			"Только у Орков и Гномов есть навыки владения этим оружием."),
	dual_daggers ("lrhand",	"парные кинжалы",	"5",    "40",   "0;0;44;120",   Material.steel,   "3000",	"9999",   "8",   "320",	"15",	"3",	"Elf", 					"Только у Эльфов есть навыки владения этим оружием."),
	long_bow     ("lrhand",	"длинный лук",		"5",    "600",  "0;0;10;0",     Material.wood,   "5000",	"9999",   "12",  "235",	"6",	"5",	null, 					"Этим оружием могут владеть все расы."),
	short_bow    ("lrhand",	"короткий лук",		"5",    "500",  "0;0;10;0",     Material.wood,   "3000",	"9999",   "12",  "275",	"6",	"5",	null, 					"Этим оружием могут владеть все расы."),
	staff        ("lrhand",	"посох",			"20",   "40",   "0;0;46;120",   Material.wood,   "4000",	"9999",   "4",   "320",	"2",	"4",	null, 					"Этим оружием могут владеть все расы."),
	rod          ("rhand",	"жезл",				"20",   "40",   "0;0;40;120",   Material.wood,    "2000",	"9999",   "4",   "360",	"2",	"1",	null, 					"Этим оружием могут владеть все расы."),
	rapier       ("rhand",	"рапира",			"5",    "40",   "0;0;40;120",   Material.steel,   "2000",	"9999",   "12",  "360",	"11",	"9",	"Kamael", 				"Только у Падших есть навыки владения этим оружием."),
	ancient_sword("lrhand",	"древний меч",		"10",   "40",   "0;0;40;120",   Material.steel,   "5000",	"9999",   "8",   "280",	"12",	"8",	"Kamael", 				"Только у Падших есть навыки владения этим оружием."),
	crossbow     ("lrhand",	"арбалет",			"5",    "400",  "0;0;40;120",   Material.wood,   "2000",	"9999",   "12",  "315",	"13",	"2",	"Kamael", 				"Только у Падших есть навыки владения этим оружием.");

	private String _bodypart;
	private String _description;
	private String _random_damage;
	private String _attack_range;
	private String _damage_range;
	private Material _material;
	private String _weight;
	private String _ench4skill;
	
	private String _rCrit;
	private String _pAtkSpd;
	
	private String _clientType;
	private String _clientHandness; // Тип держания оружия. (Например, 2 - как двуруч. блант, 4 - как посох)
	
	private String _races;
	private String _clientRaces;
	
	WeaponType(String bodypart, String descr, String rd, String ar, String dr, Material mat, String wght, String ench4skill, String rCrit, String as, String type, String hdns, String races, String cRaces)
	{
		_bodypart = bodypart;
		_description = descr;
		_random_damage = rd;
		_attack_range = ar;
		_damage_range = dr;
		_material = mat;
		_weight = wght;
		_ench4skill = ench4skill;
		
		_rCrit = rCrit;
		_pAtkSpd = as;
		
		_clientType = type;
		_clientHandness = hdns;
		
		_races = races;
		_clientRaces = cRaces;
	}
	
	public String getBodypart()
	{
		return _bodypart;
	}
	
	public String getDescription()
	{
		return _description;
	}
	
	public String getRandomDamage()
	{
		return _random_damage;
	}

	public String getAttackRange()
	{
		return _attack_range;
	}

	public String getDamageRange()
	{
		return _damage_range;
	}

	public Material getMaterial()
	{
		return _material;
	}

	public String getWeight()
	{
		return _weight;
	}
	
	public String getEnch4SkillId()
	{
		return _ench4skill;
	}

	public String getRCrit()
	{
		return _rCrit;
	}
	
	public String getPAtkSpd()
	{
		return _pAtkSpd;
	}
	
	public String getClientType()
	{
		return _clientType;
	}
	
	public String getClientHitMod()
	{
		// Формула определена аналитическим методом. Формула: 8 - rCrit.
		// как в оригинале: (rCrit - hitMod)
		// 8 - 0
		// 4 - 4
		// 12 - -3
		return String.valueOf(8 - Integer.parseInt(_rCrit));
	}
	
	public String getClientBodypart()
	{
		switch (_bodypart)
		{
			case "rhand": return "27";
			case "lrhand" : return "7";
			default: return "28";
		}
	}
	
	public String getClientHandness()
	{
		return _clientHandness;
	}
	
	public String getRaces()
	{
		return _races;
	}
	
	public String getClientRaces()
	{
		return _clientRaces;
	}
	
	public static boolean contains(String value)
	{
		for (WeaponType type : WeaponType.values())
		{
			if (type.toString().equals(value))
				return true;
		}
		
		return false;
	}
}
