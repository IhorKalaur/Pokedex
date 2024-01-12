package ihor.kalaur.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ihor.kalaur.pokedex.data.remote.PokeApi
import ihor.kalaur.pokedex.repository.PokemonRepository
import ihor.kalaur.pokedex.util.Constants.BASE_POKE_API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ) = PokemonRepository(api)

    @Singleton
    @Provides
    fun providePokeApi() : PokeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_POKE_API_URL)
            .build()
            .create(PokeApi::class.java)
    }
}
