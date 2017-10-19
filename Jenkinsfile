pipeline {
     agent any
     stages {
          stage("Compile") {
               steps {
                    sh "./mvnw clean compile"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./mvnw test"
               }
          }
          stage("Sonar Code Quality Check") {
                steps {
                    sh "./mvnw sonar:sonar"
               }
          }
          stage("Code coverage") {
               steps {
                    sh "./mvnw verify"
                    junit '*/target/site/jacoco/*.xml'
                    step( [ $class: 'JacocoPublisher' ] )

               }
          }
     }
}
