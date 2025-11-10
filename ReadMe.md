                                              Final Release Notes

1.	Main.Java
  a.	Launches the program and builds the JavaFX window.
  b.	Creates a Scene that holds the calculator interface (ArabicCalculator).
  c.	Sets the window title (“Cartis Burton: Arabic Calculator”) and size (400×350).
  d.	Calls launch(args) to start the JavaFX application.

2.	ArabicCaclulator.java
  a.	Extends GridPane to create the calculator layout.
  b.	Contains all visual elements:
    i.	Three text boxes: Arabic 1, Operator, Arabic 2
    ii.	A Result text box (non-editable)
    iii.	Three integer labels beside them showing converted integer values
  c.	Buttons:
  d.	Seven Arabic numeral buttons (غ, ث, ق, ن, ي, ه, أ)
  e.	Four operator buttons (+, −, ×, ÷)
  f.	Equals (=) button
  g.	Clear (CE) button

3.	Key layout logic:
  a.	HBox is used for the operator row.
  b.	GridPane is used for the 3×3 Arabic symbol layout.
  c.	VBox stacks the two sections together for clean alignment.
  d.	Custom colors: orange for operators, red for clear, green for equals.

4.	Extra features:
  a.	Groups Arabic symbols visually every 3 characters (formatGroupedArabic()).
  b.	Updates integer labels in real time as buttons are pressed.
  c.	CE (Clear Entry) now resets all fields and labels.

5.	Arabic.java
  a.	Handles the logic for converting Arabic numerals to integers and back.
  b.	Uses HashMaps to store and retrieve symbol values.
  c.	Methods:
    i.	setArabic(String arabicNumber) – stores the numeral and updates its integer value.
    ii.	convert_Arabic_To_Integer() – sums the symbol values (additive system).
    iii.	convert_Integer_To_Arabic(int number) – converts a number back to symbols using a greedy loop.


Additional notes:
  -	(-) always outputs a positive result because I used (Math.abs() which uses absolute values). 
  -	HashMapping was used to store value pairs and acts like a dictionary. (1000-> غ’)
    o	Initialized on start of program


<img width="468" height="639" alt="image" src="https://github.com/user-attachments/assets/0e841ee2-26df-4434-8524-22524af457fb" />
