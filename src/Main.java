import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  public static float operations(float var1, char operator, float var2) {
    switch (operator) {
      case '+':
        return (float)var1 + (float)var2;
      case '*':
        return (float)var1 * (float)var2;
      case '-':
        return (float)var1 - (float)var2;
      default:
        return (float)var1 / (float)var2;
    }
  }

  public static ArrayList<String> solutionChecker(int var1, int var2, int var3, int var4) {
    ArrayList<String> solutions = new ArrayList<String>();
    char[] operators = {'+', '*', '-', '/'};
    int[][] permutations = {
      { var1, var2, var3, var4, },
      { var1, var2, var4, var3, },
      { var1, var3, var2, var4, },
      { var1, var3, var4, var2, },
      { var1, var4, var2, var3, },
      { var1, var4, var3, var2, },
      { var2, var1, var3, var4, },
      { var2, var1, var4, var3, },
      { var2, var3, var1, var4, },
      { var2, var3, var4, var1, },
      { var2, var4, var1, var3, },
      { var2, var4, var3, var1, },
      { var3, var1, var2, var4, },
      { var3, var1, var4, var2, },
      { var3, var2, var1, var4, },
      { var3, var2, var4, var1, },
      { var3, var4, var1, var2, },
      { var3, var4, var2, var1, },
      { var4, var1, var2, var3, },
      { var4, var1, var3, var2, },
      { var4, var2, var1, var3, },
      { var4, var2, var3, var1, },
      { var4, var3, var1, var2, },
      { var4, var3, var2, var1, },
    };

    long startTime = System.currentTimeMillis();
    
    for (char i : operators) {
      for (char j : operators) {
        for (char k : operators) {
          for (int l = 0; l < 24; l++) {
            int a = permutations[l][0];
            int b = permutations[l][1];
            int c = permutations[l][2];
            int d = permutations[l][3];

            // mengecek hasil operasi
            if (operations(operations(operations(a, i, b), j, c), k, d) == 24) { // ((var opr var) opr var) opr var
              solutions.add("(" + "(" + a + i + b + ")" + j + c + ")" + k + d);
            }
            if (operations(operations(a, i, b), j, operations(c, k, d)) == 24) {  // (var opr var) opr (var opr var)
              solutions.add("(" + a + i + b + ")" + j + "(" + c + k + d + ")");
            }
            if (operations(operations(a, i, operations(b, j, c)), k, d) == 24) {  // (var opr (var opr var)) opr var
              solutions.add("(" + a + i + "(" + b + j + c + ")" + k + d + ")");
            }
            if (operations(a, i, operations(operations(b, j, c), k, d)) == 24) {  // var opr ((var opr var) opr var)
              solutions.add(a + i + "(" + "(" + b + j + c + ")" + k + d + ")");
            }
            if (operations(a, i, operations(b, j, operations(c, k, d))) == 24) { // var opr (var opr (var opr var))
              solutions.add(a + i + "(" + b + j + "(" + c + k + d + ")" + ")");
            }
          }
        }
      }
    }

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    solutions.add(Long.toString(duration));

    return solutions;
  }
  public static void main(String[] args) {
    while(true) {
      Scanner sc= new Scanner(System.in);
      System.out.println("");
      System.out.println("Opsi peng-input-an 4 buah kartu:");
      System.out.println("1. Input sendiri");
      System.out.println("2. Generate secara random");
      System.out.println("0. Keluar");
      System.out.println("");
      System.out.print("Masukkan pilihan input: ");
      int chosenOption = Integer.parseInt(sc.nextLine());
      if (chosenOption == 0) {
        System.out.println("Terima kasih telah menggunakan program ini!");
        break;
      }
      int[] cardArray = new int[4];
      boolean check = true;
      switch (chosenOption) {
        case 1:
          System.out.print("Masukkan 4 buah kartu: ");  
          String cardInput = sc.nextLine();
          String[] tempCardArray = cardInput.split(" ");
          if (tempCardArray.length != 4) {
            System.out.println("Masukan tidak sesuai");
            check = false;
            break;
          }
          for (int i = 0; i < tempCardArray.length; i++) {
            if (tempCardArray[i] == "A") {
              cardArray[i] = 1;
            }
            else if (tempCardArray[i] == "J") {
              cardArray[i] = 11;
            }
            else if (tempCardArray[i] == "Q") {
              cardArray[i] = 12;
            }
            else if (tempCardArray[i] == "K") {
              cardArray[i] = 13;
            }
            // else if (Integer.parseInt(tempCardArray[i]) > 13 || Integer.parseInt(tempCardArray[i]) < 1) {
            //   System.out.println("Masukan tidak sesuai");
            //   break;
            // }
            else {
              try {
                int temp = Integer.parseInt(tempCardArray[i]);
                if (temp > 13 || temp < 1) {
                  throw new Error();
                }
                cardArray[i] = temp;
              } catch (Exception e) {
                System.out.println("Masukan tidak sesuai");
                check = false;
                break;
              }
            }
          }
          System.out.println("");
          break;
        case 2:
          int minRange = 1;
          int maxRange = 13;
          for (int i = 0; i < 4; i++) {
            cardArray[i] = (int)Math.floor(Math.random() * (maxRange - minRange + 1) + minRange);
          }
          
          System.out.println("4 kartu yang dihasilkan: ");
          for (int i = 0; i < cardArray.length; i++) {
            if (cardArray[i] == 1) {
              System.out.print("A ");
            }
            else if (cardArray[i] == 11) {
              System.out.print("J ");
            }
            else if (cardArray[i] == 12) {
              System.out.print("Q ");
            }
            else if (cardArray[i] == 13) {
              System.out.print("K ");
            }
            else {
              System.out.print(cardArray[i] + " ");
            }
          }
          System.out.println("");
          break;
      }
      if (check) {
        System.out.println("");
        int var1 = cardArray[0];
        int var2 = cardArray[1];
        int var3 = cardArray[2];
        int var4 = cardArray[3];
        ArrayList<String> solutions = solutionChecker(var1, var2, var3, var4);
        if (solutions.size()-1 > 0) {
          System.out.println(solutions.size()-1 + " solutions found");
          for (int i = 0; i < solutions.size()-1; i++) {
            System.out.println(solutions.get(i));
          }
        }
        else {
          System.out.println("Tidak ada solusi");
        }
        System.out.println("Waktu eksekusi program: " + solutions.get(solutions.size()-1) + " milisekon");
        
        System.out.println("Apakah anda ingin menyimpan solusi? ");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.print("Masukkan pilihan penyimpanan: ");
        int fileSave = Integer.parseInt(sc.nextLine());
        if (fileSave == 1) {
          System.out.print("Nama file yang diinginkan: ");
          String fileName = sc.nextLine();
          try {
            File myObj = new File(fileName + ".txt");
            if (myObj.createNewFile()) {
              FileWriter writeContent = new FileWriter(fileName + ".txt");
              for (int i = 0; i < solutions.size()-1; i++) {
                writeContent.write(solutions.get(i)+"\n");
              }
              writeContent.close();
              System.out.println("File berhasil dibuat: " + myObj.getName());
            } else {
              System.out.println("File dengan nama tersebut telah ada.");
            }
          } catch (IOException e) {
            System.out.println("Terdapat error.");
            e.printStackTrace();
          }
        }
      }
      else {
        System.out.println("");
        System.out.println("Masukan tidak sesuai");
      }
      // sc.close();
      // break;
    }
  }
}
