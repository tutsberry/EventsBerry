<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePagesTable extends Migration {

	/**
	 * Run the migrations.
	 *
	 * @return void
	 */
	public function up()
	{
		Schema::create('pages', function(Blueprint $table)
		{
			$table->increments('id');
            $table->string('title', 100);
            $table->string('icon', 50); //will be using font awesome unicode
            $table->text('body');
            $table->boolean('active');
            $table->integer('event_id')->unsigned();
            $table->foreign('event_id')->references('id')->on('events');
			$table->timestamps();
		});
	}

	/**
	 * Reverse the migrations.
	 *
	 * @return void
	 */
	public function down()
	{
		Schema::drop('pages');
	}

}
