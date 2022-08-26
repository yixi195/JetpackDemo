package com.ysl.project.architecture.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 第三步，创建一个DataBase抽象类
 * entities：数据库相关的所有Entity实体类，
 * 他们会转化成数据库里面的表。version：数据库版本。
 * 运行时，通过调用Room.databaseBuilder()或者Room.inMemoryDatabaseBuilder()获取实例。
 * 因为每次创建Database实例都会产生比较大的开销，所以应该将Database设计成单例的，或者直接放在Application中创建。
 *  Craete by YangShlai on 2021/6/22
 */
@Database(entities = [Car::class],version = 3,exportSchema = false)
abstract class CarDatabase : RoomDatabase(){
    abstract val carDao : CarDao

    companion object {
        const val DB_NAME = "Car.db"

        //表名是从版本1升级到版本2的策略
        var MIGRATION_1_2: Migration =
            object : Migration(1,2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    //为旧表添加新的字段
                    //当添加的数据类型为 int 时，需要添加默认值 "
                    database.execSQL("ALTER TABLE cars ADD COLUMN _age INTEGER NOT NULL DEFAULT 0")
//                    // 将旧表重命名cars_temp
//                    database.execSQL("ALTER TABLE cars RENAME TO cars_temp")
//                    //创建一个新表
//                    database.execSQL("CREATE TABLE IF NOT EXISTS `cars` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_brand` TEXT,`price` INTEGER NOT NULL DEFAULT 0)")
//                    //将旧表中的数据插入新表
//                    database.execSQL("INSERT INTO cars(`_id`,`_brand`,`price`) SELECT `_id`,`_brand`,`_price` FROM cars_temp")
//                    //删除旧表
//                    database.execSQL("DROP TABLE cars_temp")
                }
            }

        var MIGRATION_2_3: Migration =
            object : Migration(2,3) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    // 为旧表添加新的字段
//                    database.execSQL("ALTER TABLE cars ADD COLUMN carEmbedded")
                    //  添加新的表
//                    database.execSQL("CREATE TABLE IF NOT EXISTS CarEmbedded (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,address TEXT, ymd TEXT)");
                }
            }

        @Volatile
        private var instance: CarDatabase? = null

        @JvmStatic
        fun get(context: Context): CarDatabase {
            return instance ?: Room.databaseBuilder(context, CarDatabase::class.java, DB_NAME)
                //.allowMainThreadQueries() //允许主线程查询
                .addMigrations(MIGRATION_1_2,MIGRATION_2_3) //添加迁移策略
                .build().also {
                    instance = it
                }
        }
    }
}