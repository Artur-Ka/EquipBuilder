package Util;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Main.Main;

/**
 * Программа для сдвига ID итемовна указанное число.<BR>
 * 
 * @author Hei
 */
public class ShiftID
{
	private static String _inFolder = Main.FOLDER + "input_xml/";
	
	private static final int SHIFT = 2; // на сколько сдвинуть
	
	private static Map<Integer, String> _lines = new HashMap<>();
	
	public static void main(String[] args)
	{
		
		File inFolderFile = new File(_inFolder);
			
		if (!inFolderFile.exists())
			inFolderFile.mkdir();
			
		if (!inFolderFile.isDirectory() || inFolderFile.listFiles().length == 0)
		{
			log("Папка с входящими данными - " + inFolderFile.getAbsolutePath() + " пуста!");
			return;
		}
		
		for (File f : inFolderFile.listFiles())
		{
			parseFile(f);
		}
	}
	
	private static void parseFile(File file)
	{
		_lines.clear();
		
		Scanner sc = null;
		PrintStream ps = null;
		
		try
		{
			sc = new Scanner(file);
			
			for (int i = 1; sc.hasNextLine(); i++)
			{
				_lines.put(i, sc.nextLine());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sc.close();
		}
		
		try
		{
			ps = new PrintStream(file);
			
			String line = null;
			String[] params = null;
			int strLength = -1;
			
			for (int i = 1; i <= _lines.size(); i++)
			{
				if (!_lines.containsKey(i))
				{
					log("Ошибка! Потеряна строка - " + i);
					return;
				}
				
				line = _lines.get(i);
				
				if (line.startsWith("/"))
					ps.println(line);
				
				else
				{
					params = line.split("\t");
					line = "";
					
					int id = Integer.parseInt(params[0]);
					int newId = id + SHIFT;
					
					if (strLength < 0)
						strLength = params.length;
					
					log(id + " --> " + newId);
					
					params[0] = String.valueOf(newId);
					
					for (int j = 0; j < params.length; j++)
					{
						line = line.concat(params[j]);
						
						if (j < strLength)
							line = line.concat("	");
					}
					
					ps.println(line);
				}	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ps.close();
		}
	}
	
	private static void log(String text)
	{
		System.out.println(text);
	}
}
