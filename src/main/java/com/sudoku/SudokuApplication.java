package com.sudoku;

import com.sudoku.buildlogic.SudokuBuildLogic;
import com.sudoku.userinterface.IUserInterfaceContract;
import com.sudoku.userinterface.UserInterfaceImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class SudokuApplication extends Application {
  private IUserInterfaceContract.View uiImpl;

  @Override
  public void start(Stage primaryStage) throws IOException {
    uiImpl = new UserInterfaceImpl(primaryStage);

    try {
      SudokuBuildLogic.build(uiImpl);
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
