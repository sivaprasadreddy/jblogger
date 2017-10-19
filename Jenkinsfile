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
                    junit 'target/surefire-reports/*.xml'
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


               }
          }
     }
     post {
         always {
             archive "target/*.jar"
             publishHTML([
                 allowMissing: false,
                 alwaysLinkToLastBuild: false,
                 keepAll: false,
                 reportDir: 'target/site/jacoco',
                 reportFiles: 'index.html',
                 reportName: 'Jacoco Report',
                 reportTitles: ''])
         }
     }
}
