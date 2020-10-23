package com.example.apes_techno.DataAccess.DBLocal

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()


fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}