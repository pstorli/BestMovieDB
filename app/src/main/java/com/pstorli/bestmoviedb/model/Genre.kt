package com.pstorli.bestmoviedb.model

import androidx.room.*
import com.pstorli.bestmoviedb.Consts
import com.pstorli.bestmoviedb.Consts.ID_DEF
import com.pstorli.bestmoviedb.Consts.ID_DEF_NAME
import com.pstorli.bestmoviedb.Consts.ID_FK_DEF

@Entity(
    tableName = Consts.GENRE_TABLE_NAME,
    foreignKeys = [ForeignKey (
        entity = Genres::class,
        parentColumns = [Consts.ID],
        childColumns = [Consts.ID_FK],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(Consts.ID_FK)]
)
data class Genre (
    @PrimaryKey (autoGenerate = true)
    var id: Int,
    var name: String,

    // NOTE: This field IS NOT part of the json record.
    // It was added solely to support ROOM sql lite db indexing.
    // PAGE Links us back to the parent class so that we can be queried.
    var idFk: Int=ID_FK_DEF) // Genre foreign key from genres
{
    constructor () : this (ID_DEF, ID_DEF_NAME, ID_FK_DEF)

    constructor (id: Int, name: String) : this (id, name, ID_FK_DEF)
}