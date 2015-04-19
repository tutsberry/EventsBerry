<?php namespace App\Http\Requests;

use App\Http\Requests\Request;

class StoreEventRequest extends Request {

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
     * @param Request $request
     * @return array
     */
	public function rules()
	{
        $rules = [
            'code' => 'required|unique:events,id,'.$this->get('id').'|max:16',
            'name' => 'required|max:200',
            'description' => 'required',
            'starts' => 'required|date',
            'ends' => 'required|date',
            'venue' => 'required'
        ];

		return $rules;
	}

}
