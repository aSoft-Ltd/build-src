try {
    tasks.getByName<Wrapper>("wrapper") {
        gradleVersion = versions.gradle
        distributionType = Wrapper.DistributionType.ALL
    }
} catch (e: Throwable) {
    
}