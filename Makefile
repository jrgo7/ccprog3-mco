main:
	del /s /q *.class
	javac src/view/gui/GUIDriver.java
	python parse-java.py > listing-uml.txt
	java src/view/gui/GUIDriver.java