pipeline {
     agent any
     stages {
          stage("Compile") {
               steps {
                    sh "./mvnw compile"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./mvnw test"
               }
          }
     }
}
