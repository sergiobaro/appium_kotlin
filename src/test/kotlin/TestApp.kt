enum class TestApp {
    LONG_TAP {
        override fun appName(): String = "longtap.zip"
    },
    UI_CATALOG {
        override fun appName(): String = "uikitcatalog.zip"
    };

    abstract fun appName(): String
}