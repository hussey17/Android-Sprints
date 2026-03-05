package com.example.listycity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    // ========================
    // Tests from the lab manual
    // ========================

    @Test
    void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities().size());

        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);
        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    void testAddException() {
        CityList cityList = mockCityList();
        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    @Test
    void testGetCities() {
        CityList cityList = mockCityList();

        // The only city should match mockCity
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        // Charlottetown < Edmonton, so it pushes Edmonton to index 1
        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
    }

    // ====================================
    // Tests for the participation exercise
    // ====================================

    @Test
    void testHasCity() {
        CityList cityList = mockCityList();

        // mockCity ("Edmonton", "Alberta") should be in the list
        assertTrue(cityList.hasCity(mockCity()));

        // A city not added should not be found
        City city = new City("Calgary", "Alberta");
        assertFalse(cityList.hasCity(city));
    }

    @Test
    void testHasCityUsesEquals() {
        // Verify that hasCity compares by value (equals), not by reference
        CityList cityList = mockCityList();

        // Create a NEW City object with the same name and province as mockCity
        City sameCityDifferentObject = new City("Edmonton", "Alberta");
        assertTrue(cityList.hasCity(sameCityDifferentObject));
    }

    @Test
    void testDelete() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.countCities());

        // Delete the only city
        cityList.delete(mockCity());
        assertEquals(0, cityList.countCities());
    }

    @Test
    void testDeleteRemovesCorrectCity() {
        CityList cityList = mockCityList();
        City city = new City("Calgary", "Alberta");
        cityList.add(city);
        assertEquals(2, cityList.countCities());

        // Delete Edmonton, Calgary should remain
        cityList.delete(mockCity());
        assertEquals(1, cityList.countCities());
        assertFalse(cityList.hasCity(mockCity()));
        assertTrue(cityList.hasCity(city));
    }

    @Test
    void testDeleteException() {
        CityList cityList = mockCityList();
        City city = new City("Calgary", "Alberta");

        // Calgary was never added, so deleting it should throw
        assertThrows(IllegalArgumentException.class, () -> {
            cityList.delete(city);
        });
    }

    @Test
    void testCountCities() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.countCities());

        cityList.add(new City("Calgary", "Alberta"));
        assertEquals(2, cityList.countCities());

        cityList.add(new City("Vancouver", "British Columbia"));
        assertEquals(3, cityList.countCities());
    }

    @Test
    void testCountCitiesEmpty() {
        CityList cityList = new CityList();
        assertEquals(0, cityList.countCities());
    }
}
