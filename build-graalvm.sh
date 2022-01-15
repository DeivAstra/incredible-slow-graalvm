#!/bin/bash

export JAVA_HOME=...
export GRAALVM_HOME=$JAVA_HOME

mvn -P graalvm clean package
