package ul.ed5042_lab2.data

import ul.ed5042_lab2.model.Module

class DataSource() {

    var moduleList: MutableList<Module> = mutableListOf<Module>()

    fun addModule(module: Module) {
        moduleList.add(module)
    }

    fun removeModule(module: Module) {
        moduleList.removeAll({mod: Module -> mod.code.equals(module.code)})
    }

    fun loadDummyData(): MutableList<Module> {
        return mutableListOf<Module>(
            Module(code = "ED5042", name = "Android1", grade = "A1", weight = 3),
            Module(code = "ED5043", name = "Android2", grade = "B1", weight = 3),
            Module(code = "ED5044", name = "Android3", grade = "C1", weight = 3),
            Module(code = "ED5045", name = "Android4", grade = "D1", weight = 3),
            Module(code = "ED5046", name = "Android5", grade = "A2", weight = 6),
            Module(code = "ED5047", name = "Android6", grade = "B2", weight = 6),
            Module(code = "ED5048", name = "Android7", grade = "C2", weight = 6),
            Module(code = "ED5049", name = "Android8", grade = "D2", weight = 6),
            Module(code = "ED5050", name = "Android9", grade = "F", weight = 6))
    }

    
}