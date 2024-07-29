main:
	del /s /q *.class
	make gui

gui:
	javac src/controller/gui/GUIDriver.java
	java src/controller/gui/GUIDriver.java

linux:
	find . -name "*.class" -type f -delete
	make gui

ts:
	del /s /q *.class 1>nul
	javac src/controller/gui/GUIDriver.java
	python.exe src/recorder/init-test-script.py

linux_ts:
	find . -name "*.class" -type f -delete
	javac src/controller/gui/GUIDriver.java
	bin/python src/recorder/init-test-script.py