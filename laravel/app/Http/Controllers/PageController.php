<?php namespace App\Http\Controllers;

use App\Event;
use App\Http\Requests;
use App\Http\Controllers\Controller;

use App\Http\Requests\StorePageRequest;
use App\Page;
use Illuminate\Http\Request;

class PageController extends Controller {

	/**
	 * Display a listing of the resource.
	 *
	 * @return Response
	 */
	public function index()
	{
        $list = Page::paginate(10);
        return view('page.list', compact('list',$list));
	}

	/**
	 * Show the form for creating a new resource.
	 *
	 * @return Response
	 */
	public function create()
	{
        $event_list = Event::lists('name', 'id');
        return view('page.new', compact('event_list', $event_list));
	}

    /**
     * Store a newly created resource in storage.
     *
     * @param StorePageRequest $request
     * @return Response
     */
	public function store(StorePageRequest $request)
	{
        $input = $request->all();

        Page::create($input);
        return redirect('page');
	}

	/**
	 * Display the specified resource.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function show($id)
	{
        $item  = Page::findOrFail($id);
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
        $event_list = Event::lists('name', 'id');
        $item  = Page::findOrFail($id);
        return view('page.edit')->with(['item' => $item, 'event_list' => $event_list]);
	}

    /**
     * Update the specified resource in storage.
     *
     * @param  int $id
     * @param StorePageRequest $request
     * @return Response
     */
	public function update($id, StorePageRequest $request)
	{
        $item  = Page::findOrFail($id);
        $request = $request->all();

        $item->update($request);
        return redirect('page');
	}

	/**
	 * Remove the specified resource from storage.
	 *
	 * @param  int  $id
	 * @return Response
	 */
	public function destroy($id)
	{
        $item  = Page::findOrFail($id);
        $item->delete();
        return ['ok' => true, 'data' => $item->toArray()];
	}

    /**
     * Toggle the status of Page
     * @param $id
     * @return \Illuminate\Http\RedirectResponse|\Illuminate\Routing\Redirector
     */
    public function toggle($id)
    {
        $item  = Page::findOrFail($id);
        $item->active = ! $item->active;
        $item->update();
        return redirect('page');

    }

}
