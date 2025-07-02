package md.keeproblems.mydailyroutine.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

data class RoutineTheme(
    val primaryColor: Color,
    val secondaryColor: Color = Color.Unspecified,
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color = Color.Unspecified,
) {
    companion object {
        val defaultRoutineTheme: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = MaterialTheme.colorScheme.primary,
                primaryBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                secondaryColor = Color.Unspecified,
                secondaryBackgroundColor = Color.Unspecified
            )

        val bluePastel: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFFFBEAEB),
                primaryBackgroundColor = Color(0xFF2F3C7E),
            )

        val darkCharcoalBrightYellow: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFFFEE715),    // ярко-жёлтый
                primaryBackgroundColor = Color(0xFF101820)  // тёмный угольный
            )

        val lightRedYellow: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFFF96167),    // светло-красный
                primaryBackgroundColor = Color(0xFFF9E795)  // желтоватый светлый
            )

        val cherryRedOffWhite: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFF990011),    // вишнёвый красный
                primaryBackgroundColor = Color(0xFFFCF6F5)  // почти белый
            )

        val babyBlueWhite: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFF8AAAE5),    // детский голубой
                primaryBackgroundColor = Color(0xFFFFFFFF)  // белый
            )

        val darkBlueLightBlue: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFF00246B),    // тёмно-синий
                primaryBackgroundColor = Color(0xFFCADCFC)  // светло-голубой
            )

        val skyBlueBubblegumPink: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFF89ABE3),    // небесно-голубой
                primaryBackgroundColor = Color(0xFFEA738D)  // жвачечно-розовый
            )

        val cherryRedBubblegumPink: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFFCC313D),    // вишнёвый красный
                primaryBackgroundColor = Color(0xFFF7C5CC)  // жвачечно-розовый светлый
            )

        val forestGreenMossGreen: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = Color(0xFF2C5F2D),    // лесной зелёный
                primaryBackgroundColor = Color(0xFF97BC62)  // моховой зелёный светлый
            )

    }
}


enum class RoutineThemes(val displayName: String) {
    @SerializedName("DEFAULT")
    DEFAULT("Default"),

    @SerializedName("BLUE_PASTEL")
    BLUE_PASTEL("Blue Pastel"),

    @SerializedName("DARK_CHARCOAL_BRIGHT_YELLOW")
    DARK_CHARCOAL_BRIGHT_YELLOW("Dark Charcoal & Bright Yellow"),

    @SerializedName("LIGHT_RED_YELLOW")
    LIGHT_RED_YELLOW("Light Red & Yellow"),

    @SerializedName("CHERRY_RED_OFF_WHITE")
    CHERRY_RED_OFF_WHITE("Cherry Red & Off White"),

    @SerializedName("BABY_BLUE_WHITE")
    BABY_BLUE_WHITE("Baby Blue & White"),

    @SerializedName("DARK_BLUE_LIGHT_BLUE")
    DARK_BLUE_LIGHT_BLUE("Dark Blue & Light Blue"),

    @SerializedName("SKY_BLUE_BUBBLEGUM_PINK")
    SKY_BLUE_BUBBLEGUM_PINK("Sky Blue & Bubblegum Pink"),

    @SerializedName("CHERRY_RED_BUBBLEGUM_PINK")
    CHERRY_RED_BUBBLEGUM_PINK("Cherry Red & Bubblegum Pink"),

    @SerializedName("FOREST_GREEN_MOSS_GREEN")
    FOREST_GREEN_MOSS_GREEN("Forest Green & Moss Green")
}


@Composable
fun getRoutineThemeByType(routineThemes: RoutineThemes): RoutineTheme {
    return when (routineThemes) {
        RoutineThemes.BLUE_PASTEL -> RoutineTheme.bluePastel
        RoutineThemes.DARK_CHARCOAL_BRIGHT_YELLOW -> RoutineTheme.darkCharcoalBrightYellow
        RoutineThemes.LIGHT_RED_YELLOW -> RoutineTheme.lightRedYellow
        RoutineThemes.CHERRY_RED_OFF_WHITE -> RoutineTheme.cherryRedOffWhite
        RoutineThemes.BABY_BLUE_WHITE -> RoutineTheme.babyBlueWhite
        RoutineThemes.DARK_BLUE_LIGHT_BLUE -> RoutineTheme.darkBlueLightBlue
        RoutineThemes.SKY_BLUE_BUBBLEGUM_PINK -> RoutineTheme.skyBlueBubblegumPink
        RoutineThemes.CHERRY_RED_BUBBLEGUM_PINK -> RoutineTheme.cherryRedBubblegumPink
        RoutineThemes.FOREST_GREEN_MOSS_GREEN -> RoutineTheme.forestGreenMossGreen
        else -> RoutineTheme.defaultRoutineTheme
    }
}

@Composable
fun MaterialTheme.defaultRoutineTheme(): RoutineTheme = RoutineTheme.defaultRoutineTheme