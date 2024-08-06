pipeline {
    agent { node { label 'testlabel' } }
    stages {
        stage('test') {
            steps {
                sh 'echo Test print'
            }
        }
        stage('build') {
            steps {
                sh 'node --version'
            }
        }
    }
}
