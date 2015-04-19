<?php namespace App;

use Illuminate\Database\Eloquent\Model;

class Page extends Model {

    //Guarded property of model
	protected $guarded = ['id'];

    //Hidden fields
    protected $hidden  = ['event_id', 'active'];

    //Cast fields types
    protected $casts = [
        'event_id' => 'integer',
        'id'      => 'integer',
    ];

    //Define Relationship
    public function event(){
        return $this->belongsTo('App\Event');
    }
}
