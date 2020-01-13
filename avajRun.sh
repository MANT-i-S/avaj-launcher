find . -name "*.java" > sources.txt
javac -sourcepath . @sources.txt
java avaj.weather.Simulator scenario.txt