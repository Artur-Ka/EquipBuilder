package Main;

import java.util.Scanner;

import Managers.DatManager;
import Managers.XMLManager;

public class Main
{
	public static final String FOLDER = "D:\\EquipBuild/";
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===== Загрузка XML =====");
		XMLManager.getInstance().load();
		
		System.out.print("Сбилдить Dat? [y/n] ");
		
		if (sc.next().equalsIgnoreCase("y"))
		{
			System.out.println("===== Загрузка DAT =====");
			DatManager.getInstance().load();
		}
		
		System.out.println("Завершено!");
		sc.close();
	}
	
	public static void log(Object o, String text)
	{
		if (o == null)
			System.out.println("[]: " + text);
		else
			System.out.println("[" + o.getClass().getName() + "]: " + text);
	}
}
