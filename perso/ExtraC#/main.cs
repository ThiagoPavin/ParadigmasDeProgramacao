using System;
using System.Collections.Generic;
using System.Collections;

class RandomPicker {

  public static void Main (string[] args) {
    List<string> liststr = new List<string>();
    RandomPicker.passaNomes(liststr);
    List<string> listShuffled = Shuffle(liststr);
    Console.WriteLine("\n");
    listShuffled.ForEach(Console.WriteLine);
  }

  public static void passaNomes(List<string> list){
    string path = System.AppDomain.CurrentDomain.BaseDirectory.ToString();

    string[] lines = System.IO.File.ReadAllLines(path + "Nomes.txt");

    foreach (string l in lines){
      Console.WriteLine(l);
      list.Add(l);
    }
  }

  public static List<string> Shuffle(List<string> list){
    List<string> liststr = new List<string>();

    Random r = new Random();
    int randomIndex = 0;
    while (list.Count > 0){
          randomIndex = r.Next(0, list.Count); //Choose a random object in the list
          liststr.Add(list[randomIndex]); //add it to the new, random list
          list.RemoveAt(randomIndex); //remove to avoid duplicates
    }
    return liststr; //return the new random list
  }
}
