package com.example.utsapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.utsapp", appContext.packageName)
    }

    @Test
    fun testLocalStorageManagerMultipleItems() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val localStorageManager = LocalStorageManager(appContext)

        // Clear initial data
        localStorageManager.clearData()
        assertEquals(0, localStorageManager.getDataCount())

        // Add 2 records
        localStorageManager.addData("Mahasiswa A", "1301210001", "Catatan A")
        localStorageManager.addData("Mahasiswa B", "1301210002", "Catatan B")

        val list = localStorageManager.getAllData()
        assertEquals(2, list.size)
        assertEquals("Mahasiswa A", list[0].nama)
        assertEquals("Mahasiswa B", list[1].nama)

        // Delete first record by ID
        localStorageManager.deleteDataById(list[0].id)
        val listAfterDelete = localStorageManager.getAllData()
        assertEquals(1, listAfterDelete.size)
        assertEquals("Mahasiswa B", listAfterDelete[0].nama)

        // Cleanup
        localStorageManager.clearData()
        assertEquals(0, localStorageManager.getDataCount())
    }
}
