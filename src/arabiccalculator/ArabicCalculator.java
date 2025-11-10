/*
 * This class creates the Arabic calculator GUI.
 * It lets the user input Arabic numerals, pick an operator,
 * and then shows both the Arabic and integer results.
`* I used the textbook and online JavaFX examples for layout ideas.
 */
package arabiccalculator;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ArabicCalculator extends GridPane {

    // Text fields for inputs and result
    private TextField tfArabic1 = new TextField();
    private TextField tfArabic2 = new TextField();
    private TextField tfResult = new TextField();

    // Integer value labels for each line
    private Label lblInt1 = new Label("= ");
    private Label lblInt2 = new Label("= ");
    private Label lblIntResult = new Label("= ");
    private TextField tfOperator = new TextField();

    // Remembers the current operator (+, -, *, /)
    private String operator = "";

    public ArabicCalculator() {
        // Creating the window via grids
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(12);
        setStyle("-fx-background-color: #fafafa;");
        setAlignment(Pos.CENTER);

        tfArabic1.setStyle("-fx-font-family: 'Arial';");
        tfArabic2.setStyle("-fx-font-family: 'Arial';");
        tfResult.setStyle("-fx-font-family: 'Arial'; -fx-control-inner-background: #f0f0f0;");

        lblInt1.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold;");
        lblInt2.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold;");
        lblIntResult.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold;");

        // Removing editable field types to force button presses
        tfArabic1.setEditable(false);
        tfArabic2.setEditable(false);
        tfResult.setEditable(false);

        tfOperator.setEditable(false);
        tfOperator.setPrefWidth(60);
        tfOperator.setStyle(
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-control-inner-background: #f0f0f0;"
        );

        Label lbl1 = new Label("Arabic 1:");
        lbl1.setMinWidth(80);
        Label lblOp = new Label("Operator:");
        lblOp.setMinWidth(80);
        Label lbl2 = new Label("Arabic 2:");
        lbl2.setMinWidth(80);
        Label lbl3 = new Label("Result:");
        lbl3.setMinWidth(80);

        // First section: labels, fields, then integer results
        add(lbl1, 0, 0);
        add(tfArabic1, 1, 0);
        add(lblInt1, 2, 0);

        add(lblOp, 0, 1);
        add(tfOperator, 1, 1);

        add(lbl2, 0, 2);
        add(tfArabic2, 1, 2);
        add(lblInt2, 2, 2);

        add(lbl3, 0, 3);
        add(tfResult, 1, 3);
        add(lblIntResult, 2, 3);

        // Button sizes and spacing
        double numButtonSize = 60;
        int hGap = 4;

        // Operator row uses HBox: +  -  *  /
        HBox opRow = new HBox(hGap);
        opRow.setAlignment(Pos.CENTER);

        // I chose a slightly smaller width so the operator row of four
        // lines up with the rows of three
        double opButtonWidth = (3 * numButtonSize - hGap) / 4.0;

        String[] ops = {"+", "-", "*", "/"};

        for (String op : ops) {
            Button btn = new Button(op);
            btn.setFont(Font.font("Arial", FontWeight.BOLD, 22));
            btn.setPadding(new Insets(0));
            btn.setPrefSize(opButtonWidth, numButtonSize);

            // Center the text horizontally. Still need to figure out how to center "*" vertically
            String style = "-fx-background-color: #f4a261; " +
                           "-fx-alignment: center; " +
                           "-fx-text-alignment: center;";

            btn.setStyle(style);

            btn.setOnAction(e -> {
                operator = op;
                tfOperator.setText(op);
            });

            opRow.getChildren().add(btn);
        }

        // GridPane for the remaining: Arabic symbols plus CE and =
        GridPane digitGrid = new GridPane();
        digitGrid.setHgap(hGap);
        digitGrid.setVgap(4);
        digitGrid.setPadding(Insets.EMPTY);
        digitGrid.setAlignment(Pos.CENTER);

        String[][] digitLayout = {
                {"غ", "ث", "ق"},
                {"ن", "ي", "ه"},
                {"CE", "أ", "="}
        };

        for (int row = 0; row < digitLayout.length; row++) {
            for (int col = 0; col < digitLayout[row].length; col++) {
                String symbol = digitLayout[row][col];

                Button btn = new Button(symbol);
                btn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                btn.setPadding(new Insets(0));
                btn.setPrefSize(numButtonSize, numButtonSize);

                if ("CE".equals(symbol)) {
                    btn.setStyle("-fx-background-color: #e76f51; -fx-text-fill: white;");
                    btn.setOnAction(e -> clearAll());
                } else if ("=".equals(symbol)) {
                    btn.setStyle("-fx-background-color: #2a9d8f; -fx-text-fill: white;");
                    btn.setOnAction(e -> calculate());
                } else {
                    // Regular Arabic digit button
                    btn.setOnAction(e -> insertDigit(symbol));
                }

                digitGrid.add(btn, col, row);
            }
        }

        // Stacked the operator row on top of the digit grid
        VBox buttonGroup = new VBox(8, opRow, digitGrid);
        buttonGroup.setPadding(new Insets(10));
        buttonGroup.setAlignment(Pos.CENTER);
        buttonGroup.setStyle(
                "-fx-background-color: #f7f7f7; " +
                "-fx-border-color: #ddd; " +
                "-fx-border-radius: 6; " +
                "-fx-background-radius: 6;"
        );

        // Place the whole button block under the fields
        add(buttonGroup, 0, 4, 3, 3);
    }

    // For readability I added a space after every three symbols. Visual only.
    private String formatGroupedArabic(String rawArabic) {
        StringBuilder grouped = new StringBuilder();
        int len = rawArabic.length();

        for (int i = 0; i < len; i++) {
            grouped.append(rawArabic.charAt(i));
            if ((len - i - 1) % 3 == 0 && i != len - 1) {
                grouped.append(" ");
            }
        }
        return grouped.toString();
    }

    
    // Adds the symbol to the correct input box based on
    // whether the operator was chosen yet or not.
    private void insertDigit(String digit) {
        if (operator.isEmpty()) {
            tfArabic1.appendText(digit);
            updateLabel(tfArabic1.getText(), lblInt1);
        } else {
            tfArabic2.appendText(digit);
            updateLabel(tfArabic2.getText(), lblInt2);
        }
    }


    // Converts Arabic string to int and displays it.
    // Error will generate a "?"
    private void updateLabel(String arabicStr, Label lbl) {
        try {
            Arabic a = new Arabic(arabicStr);
            lbl.setText("= " + a.convert_Arabic_To_Integer());
        } catch (Exception e) {
            lbl.setText("= ?");
        }
    }

    /* 
     * Converts both inputs to ints using the Arabic class.
     * runs the operation, then converts back into Arabic for the result.
    */
    private void calculate() {
        try {
            Arabic a1 = new Arabic(tfArabic1.getText());
            Arabic a2 = new Arabic(tfArabic2.getText());

            int num1 = a1.convert_Arabic_To_Integer();
            int num2 = a2.convert_Arabic_To_Integer();
            int result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    // I take the absolute value so the result is not negative
                    result = Math.abs(num1 - num2);
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    // Simple guard against divide by zero
                    result = (num2 == 0) ? 0 : num1 / num2;
                    break;
            }

            Arabic resArabic = new Arabic();
            String arabicResult = resArabic.convert_Integer_To_Arabic(result);

            tfResult.setText(formatGroupedArabic(arabicResult));
            lblIntResult.setText("= " + result);

        } catch (Exception e) {
            tfResult.setText("Error");
            lblIntResult.setText("= ?");
        }
    }


    // Resets everything so the user can start a over.
    private void clearAll() {
        tfArabic1.clear();
        tfArabic2.clear();
        tfResult.clear();

        lblInt1.setText("= ");
        lblInt2.setText("= ");
        lblIntResult.setText("= ");

        operator = "";
        tfOperator.clear();
    }
}