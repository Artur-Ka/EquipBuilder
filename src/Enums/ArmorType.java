package Enums;

public enum ArmorType
{
	chest(		"chest",		"доспех",			"21"),
	legs(		"legs",			"поножи",			"22"),
	full(		"full",			"доспех",			"8"),
	gloves(		"gloves",		"перчатки",			"20"),
	boots(		"boots",		"ботинки",			"23"),
	shield(		"lhand",							"28",	Material.steel,		"6000"),
	neck(		"neck",								"3",	Material.gold,		"1000"),
	earring(	"rear;lear",						"1",	Material.gold,		"800"),
	ring(		"rfinger;lfinger",					"4",	Material.gold,		"600"),
	talisman(	"talisman",							"6",	Material.silver,	"1000"),
	shirt(		"underwear",						"21",	Material.cloth,		"1000"),
	belt(		"belt",								"19",	Material.leather,	"1000"),
	bag(		"bag",								"24",	Material.leather,	"1000");
	
	/**
	 * ordinal() - id типа в клиенте
	 * 
	 * @author Hei
	 */
	public enum Type
	{
		none(	null,			null,			null),
		light(	"лёгкий",		"лёгкие",		Material.leather),
		heavy(	"тяжёлый",		"тяжёлый",		Material.steel),
		robe(	"магический",	"магические",	Material.cloth);
		
		private String _chestDescr;
		private String _partsDescr;
		private Material _material;
		
		Type(String chestDescr, String partsDescr, Material material)
		{
			_chestDescr = chestDescr;
			_partsDescr = partsDescr;
			_material = material;
		}
		
		public String getChestDescr()
		{
			return _chestDescr;
		}
		
		public String getPartsDescr()
		{
			return _partsDescr;
		}
		
		public static boolean contains(String value)
		{
			for (Type type : Type.values())
			{
				if (type.toString().equals(value))
					return true;
			}
			
			return false;
		}
	}
	
	private String _description;
	private final String _bodypart;
	private Material _material;
	private String _weight;
	
	private final String _clientBodypart;
	
	ArmorType(String bodypart, String descr, String cBodypart)
	{
		_bodypart = bodypart;
		_description = descr;
		
		_clientBodypart = cBodypart;
	}
	
	ArmorType(String bodypart, String cBodypart, Material material, String weight)
	{
		_bodypart = bodypart;
		_material = material;
		_weight = weight;
		
		_clientBodypart = cBodypart;
	}
	
	public String getDescription()
	{
		return _description != null ? _description : toString(); 
	}
	
	public String getBodypart()
	{
		return _bodypart;
	}
	
	public String getEvasion()
	{
		return "-8";
	}
	
	public String getShieldRate()
	{
		return "10";
	}
	
	/**
	 * Если нет типа (хеви, лайт, роба) - указать в качестве параметра null.<BR><BR>
	 * @param type
	 * @return
	 */
	public Material getMaterial(Type type)
	{
		if (type != null && type._material != null)
			return type._material;
		
		return _material;
	}
	
	/**
	 * Если нет типа (хеви, лайт, роба) - указать в качестве параметра null.<BR><BR>
	 * @param type
	 * @return
	 */
	public String getWeight(Type type)
	{
		if (type == null)
			return _weight;
		
		switch (type)
		{
			case heavy:
			{
				switch (this)
				{
					case chest: return "6000";
					case legs: return "4000";
					case full: return "10000";
					case gloves: return "2000";
					case boots: return "2000";
				}
			}
			case light:
			{
				switch (this)
				{
					case chest: return "5000";
					case legs: return "3000";
					case full: return "8000";
					case gloves: return "1500";
					case boots: return "1500";
				}
			}
			case robe:
			{
				switch (this)
				{
					case chest: return "4000";
					case legs: return "2000";
					case full: return "6000";
					case gloves: return "1000";
					case boots: return "1000";
				}
			}
		}
		
		return null;
	}
	
	public String getClientBodypart()
	{
		return _clientBodypart;
	}
	
	public static boolean contains(String value)
	{
		for (ArmorType type : ArmorType.values())
		{
			if (type.toString().equals(value))
				return true;
		}
		
		return false;
	}
}
