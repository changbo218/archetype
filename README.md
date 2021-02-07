```bash
mvn archetype:generate -DarchetypeGroupId=com.changbo.is -DarchetypeArtifactId=is-springboot-archetype \
  -DarchetypeVersion=1.0.0-master-BETA-SNAPSHOT -DinteractiveMode=false -DarchetypeCatalog=local \
  -DgroupId=${groupId} -DartifactId=${artifactId} -DprojectName=${projectName} -Dpackage=${package}
```

**demo**
```bash
mvn archetype:generate -DarchetypeGroupId=com.changbo.is -DarchetypeArtifactId=is-spring-boot-archetype\
  -DarchetypeVersion=1.0.0-master-BETA-SNAPSHOT -DinteractiveMode=false -DarchetypeCatalog=local \
  -DgroupId=changbo -DartifactId=is-demo -DprojectName=is-demo-archetype -Dpackage=com.changbo.is.demo
```