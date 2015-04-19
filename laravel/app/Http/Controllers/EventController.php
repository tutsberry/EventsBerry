<?php namespace App\Http\Controllers;

use App\Event;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Http\Requests\StoreEventRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Input;

class EventController extends Controller {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
		$list = Event::paginate(10);
		return view('event.list', compact('list',$list));
	}

	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
		return view('event.new');
	}

	/**
	 * Store a newly created resource in storage.
	 *
	 * @param StoreEventRequest $request
	 * @return Response
	 */
	public function store(StoreEventRequest $request)
	{
        $input = $request->all();
        //Add the logged in user ID
        $input['user_id'] = \Auth::user()->id;

        //Handle image uploads
        $input['param']['logo'] = $this->uploadImg(Input::file('param.logo'));
        $input['param']['splash_screen'] = $this->uploadImg(Input::file('param.splash_screen'));

		Event::create($input);
		return redirect('event');
	}

	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
        //Get the event with ID and online is 1
		$item  = Event::where('online', 1)->findOrFail($id);
        //Add related pages also for this event
        $item['pages'] = $item->pages()->where('active',1)->get();

		return $item;
	}

	/**
	 * Show the form for editing the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function edit($id)
	{
		$item  = Event::findOrFail($id);
		return view('event.edit', compact('item', $item));
	}

	/**
	 * Update the specified resource in storage.
	 *
	 * @param  int $id
	 * @param StoreEventRequest $request
	 * @return Response
	 */
	public function update($id, StoreEventRequest $request)
	{

		$item  = Event::findOrFail($id);
		$request = $request->all();

        //Handle img uploads
        $request['param']['logo'] = $this->uploadImg(Input::file('param.logo'), $item['param']['logo']);
        $request['param']['splash_screen'] = $this->uploadImg(Input::file('param.splash_screen'), $item['param']['splash_screen']);

		$item->update($request);
		return redirect('event');
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
        $item  = Event::findOrFail($id);
        $item->delete();
        return ['ok' => true, 'data' => $item->toArray()];
	}


    /**
     * Toggle the online
     * @param $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function toggle($id)
    {
        //Get the event by ID
        $item  = Event::findOrFail($id);
        //Toggle the online property
        $item->online = ! $item->online;
        $item->update();

        return redirect('event');
    }

    /**
     * Upload image and return URL
     * @param $file
     * @param null $oldFile
     * @return null
     */
    private function uploadImg($file, $oldFile = null)
    {
        //Validate the type of Img
        if( ! is_null($file) && strpos($file->getClientMimeType(),'image') !== FALSE) {

            //create filename without space
            $filename = str_replace(' ', '-', $file->getClientOriginalName());
            //Set the destination path
            $uploadDir = "/uploads/";
            $destinationPath = public_path().$uploadDir;

            //Upload the file
            if($file->move($destinationPath, $filename) ) {
               //Delete old file
               $trashFile = $destinationPath.basename($oldFile);
               if(file_exists($trashFile)) {
                   @unlink($trashFile);
               }
               //Return the new updated URL
               return \URL::to($uploadDir.$filename);
           }
        }

        return $oldFile;
    }

}
