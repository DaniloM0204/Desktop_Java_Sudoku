package com.sudoku.persistence;

import com.sudoku.problemdomain.IStorage;
import com.sudoku.problemdomain.SudokuGame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalStorageImpl implements IStorage {
  private static File GAME_DATA = new File(
    System.getProperty("user.home"),
    "gamedata.txt"
  );

  @Override
  public void updateGameData(SudokuGame game) throws IOException {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(
        fileOutputStream
      );
      objectOutputStream.writeObject(game);
      objectOutputStream.close();
    } catch (IOException e) {
      throw new IOException("Unable to access Game Data");
    }
  }

  @Override
  public SudokuGame getGameData() throws IOException {
    FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
    try (
      ObjectInputStream objectInputStream = new ObjectInputStream(
        fileInputStream
      )
    ) {
      try {
        SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
        return gameState;
      } catch (ClassNotFoundException e) {
        throw new IOException("File Not Found");
      }
    }
  }
}
