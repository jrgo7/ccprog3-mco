main:
	javac *.java
	python parse-java.py > listing-uml.txt
	java GUIDriver.java