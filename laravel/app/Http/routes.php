<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', 'WelcomeController@index');

Route::controllers([
    'auth' => 'Auth\AuthController',
    'password' => 'Auth\PasswordController',
]);

//Protected routes, only logged in user can access
Route::group(['middleware' => 'auth'], function()
{
    //Toggle state of event
    Route::get('event/toggle/{id}', 'EventController@toggle');
    //Resource route for event
    Route::resource('event', 'EventController');

    //Toggle state of page
    Route::get('page/toggle/{id}', 'PageController@toggle');
    //Resource route for page
    Route::resource('page', 'PageController');

    //Default home route
    Route::get('home', 'EventController@index');
});

//API Route for Public
Route::group(['prefix' => 'api'], function()
{
    //To get event by ID or Code Name
    Route::get('event/{id}', 'EventController@show');
});
