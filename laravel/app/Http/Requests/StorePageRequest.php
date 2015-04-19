<?php namespace App\Http\Requests;

use App\Http\Requests\Request;

class StorePageRequest extends Request {

	/**
	 * Determine if the user is authorized to make this request.
	 *
	 * @return bool
	 */
	public function authorize()
	{
        return \Auth::check();
	}

	/**
	 * Get the validation rules that apply to the request.
	 *
	 * @return array
	 */
	public function rules()
	{
		return [
			'title' => 'required|max:100',
            'icon'  => 'required',
            'body'  => 'required',
            'event_id' => 'required|integer'
		];
	}
}
