#!/bin/bash

mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test org.jacoco:jacoco-maven-plugin:report

#https://www.jacoco.org/jacoco/trunk/doc/maven.html