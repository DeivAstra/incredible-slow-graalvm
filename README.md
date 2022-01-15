# incredible-slow-graalvm
Incredible slow (up to 10 times) graalvm native-image vs java 

```
/// Results (AMD Ryzen 7 5800H)

// JDK 17 (OpenJDK 64-Bit Server VM GraalVM CE 21.3.0 (build 17.0.1+12-jvmci-21.3-b05, mixed mode, sharing)
generateRandomData >
end generateRandomData (2937 ms)
search synonyms in data >
end search synonyms (4564 ms)

$ ./incredible-slow-graalvm_ce-java17-21.3.0
generateRandomData >
end generateRandomData (29441 ms) !!! slow to 10 times !!!
search synonyms in data >
end search synonyms (11510 ms)

$ ./incredible-slow-graalvm_ce-22.1.0-dev-20220113_2228
generateRandomData >
end generateRandomData (55176 ms) !!! more slower than 21.3.0 !!!
search synonyms in data >
end search synonyms (11433 ms)
```
