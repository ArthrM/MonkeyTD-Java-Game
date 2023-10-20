package helperMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import objects.PathPoint;

public class LoadSave {

	public static BufferedImage getSpriteAtlas() {

		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");
		
		// Tenta importar a imagem, se falhar, mostra uma mensagem de erro
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {          
			e.printStackTrace();                
		}
		
		return img;
	}
	
	//Cria um arquivo de texto
	public static void CreateFile() {
		
		File txtFile = new File("res/testTextFile.txt");
		
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void CreateLevel(String name, int[] idArr) {
		
		File newLevel = new File("res/" + name + ".txt");
		if(newLevel.exists()) {
			System.out.println("Arquivo: " + name + " Ja existe.");
			return;
		} else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			WriteToFile(newLevel, idArr, new PathPoint(0,0), new PathPoint(0, 0));
		}
		
	}
	
	
	 //Escreve em um arquivo de texto existente
	private static void WriteToFile(File f, int[] idArr, PathPoint start, PathPoint end) {
		try {
			PrintWriter pw = new PrintWriter(f);
			for(Integer i : idArr)
				pw.println(i);
			pw.println(start.getxCord());
			pw.println(start.getyCord());
			pw.println(end.getxCord());
			pw.println(end.getyCord());
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint end) {
		File levelFile = new File("res/" + name + ".txt");
		
		if(levelFile.exists()) {
			WriteToFile(levelFile, UtilMethods.TwoDto1DintArr(idArr), start, end);
		} else {
			System.out.println("Arquivo: " + name + " Nao foi econtrado.");
			return;
		}
	}
	
	
	//Lê um arquivo de texto existente
	private static ArrayList<Integer> ReadFromFile(File file) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		return list;
	}
	
	public static ArrayList<PathPoint> GetLevelPathPoints(String name) {
		File lvlFile = new File("res/" + name + ".txt");
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile(lvlFile);
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400), list.get(401)));
			points.add(new PathPoint(list.get(402), list.get(403)));
			
			return points;
			
		} else {
			System.out.println("Arquivo: " + name + " Nao foi econtrado.");
			return null;
		}
	}
	
	public static int[][] GetLevelData(String name) {
		File lvlFile = new File("res/" + name + ".txt");
		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile(lvlFile);
			return UtilMethods.ArrayListTo2Dint(list, 20, 20);
		} else {
			System.out.println("Arquivo: " + name + " Nao foi econtrado.");
			return null;
		}
		
		
	}
	
	
	
}
