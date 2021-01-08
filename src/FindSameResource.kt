import java.io.File

fun main(args: Array<String>) {
    val root = File("/Users/lls/Documents/Overlord")
    //构造一个kv的conflictFiles，k是resType + resName, v是满足k情况下的资源列表
    //resType: drawable(drawable-xxx) layout(layout-xxx) raw color anim animator
    val conflictFiles = HashMap<String, ArrayList<String>>()
    findSrcDir(root, conflictFiles)
    println(conflictFiles.filter { it.value.size > 1 })
}

fun convertResFileName(path: String): String? {
    val pathFileNamePart = path.split("/src/main/res/").getOrNull(1) ?: return null
    val fileNameIgnoreSuffix = pathFileNamePart.split("/").getOrNull(1)?.split(".")?.getOrNull(0) ?: return null
    val rawType = pathFileNamePart.substring(0, pathFileNamePart.indexOfFirst { it == '/' })
    val resType = when {
        rawType.startsWith("drawable") -> "drawable"
        rawType.startsWith("layout") -> "layout"
        rawType.startsWith("raw") -> "raw"
        rawType.startsWith("color") -> "color"
        rawType.startsWith("anim") -> "anim"
        rawType.startsWith("animator") -> "animator"
        else -> return null
    }
    return "$resType/$fileNameIgnoreSuffix"
}

// /Users/lls/Documents/Overlord/common-app/exercise/src/main/res/anim/cc_lesson_dictation_down.xml
fun findModuleName(path: String): String {
    val pathModulePart = path.split("/src/main/res/").getOrNull(0) ?: return ""
    return pathModulePart.substring(pathModulePart.indexOfLast { it == '/' } + 1)
}

fun findSrcDir(file: File, found: HashMap<String, ArrayList<String>>) {
    if (file.isDirectory) {
        if (file.name != "src") {
            file.listFiles().forEach {
                findSrcDir(it, found)
            }
        } else {
            findResDir(file, found)
        }
    }
}

fun findResDir(file: File, found: HashMap<String, ArrayList<String>>) {
    if (file.isDirectory) {
        if (file.name != "res") {
            file.listFiles().forEach {
                findResDir(it, found)
            }
        } else {
            findSameFile(file, found)
        }
    }
}

fun findSameFile(file: File, found: HashMap<String, ArrayList<String>>) {
    if (file.isDirectory) {
        file.listFiles().forEach {
            findSameFile(it, found)
        }
    } else {
        val fileName = convertResFileName(file.absolutePath) ?: return
        val arr = found.getOrElse(fileName) { ArrayList() }
        val moduleName = findModuleName(file.absolutePath)
        if (!arr.contains(moduleName)) {
            arr.add(moduleName)
        }
        found[fileName] = arr
    }
}