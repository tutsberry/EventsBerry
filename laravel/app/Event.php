<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Event extends Model {

    //Guarded property of model
	protected $guarded = ['id'];

    //Hidden fields
    protected $hidden  = ['online', 'user_id'];

    //Cast fields types
    protected $casts = [
        'user_id' => 'integer',
        'id'      => 'integer',
        'param'   => 'array'
    ];

    //Define Relationship
    public function user() {
        return $this->belongsTo('App\User');
    }

    public function pages(){
        return $this->hasMany('App\Page');
    }

}
