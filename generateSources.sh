#!/bin/sh
echo "Finding all Java classes..."
find -name "*.java" > sources.txt
javac @sources.txt