package com.br.karen.composeshoes.di.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.br.karen.composeshoes.dao.AppDao
import com.br.karen.composeshoes.database.AppDatabase
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.ui.viewmodel.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    single { get<AppDatabase>().appDao() }

    single(createdAtStart = true) {
        val dao = get<AppDao>()

        CoroutineScope(Dispatchers.IO).launch {
            if (dao.getAllProducts().isEmpty()) {
                dao.saveProduct(
                    Product(
                        name = "Chuteira Nike Tiempo 10",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 245.99
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Nike Air Max Dn Essential",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 699.00
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Nike Air Max 2013",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 920.00
                    )
                )
                dao.saveProduct(
                    Product(
                        name = "Nike Air Zoom Upturn SC",
                        description = "Dê as boas-vindas à próxima geração do Air Max. Este modelo contemporâneo e" +
                                " deslumbrante homenageia o rico legado do Air Max com um cabedal em mesh multicamadas" +
                                " com estampa tátil e iconografia tradicional, detalhes foscos para um visual magnético" +
                                " e um inovador sistema de unidade Dynamic Air, projetado para fazer com que caminhar pareça" +
                                " deslizar no ar.",
                        price = 399.99
                    )
                )

            }
        }
    }
}
