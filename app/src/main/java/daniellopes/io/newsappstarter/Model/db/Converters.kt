package daniellopes.io.newsappstarter.Model.db

import androidx.room.TypeConverter
import daniellopes.io.newsappstarter.Model.entity.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) : String{
        return source.name
    }

    fun toSource(name:String): Source{
        return Source(name, name)
    }
}