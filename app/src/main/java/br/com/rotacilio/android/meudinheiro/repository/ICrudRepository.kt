package br.com.rotacilio.android.meudinheiro.repository

interface ICrudRepository<T> {
    suspend fun createOrUpdate(data: T): T
    suspend fun create(data: T): T
    suspend fun update(data: T): T
    suspend fun delete(data: T)
    suspend fun findById(id: Long): T?
    suspend fun findAll(): List<T>
}