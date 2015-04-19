@extends('app')

@section('content')
    <div class="col-md-6">
       <h3 class="modal-title">Events</h3>
    </div>
    <div class="col-md-6 text-right">
        <p>
            <a href="{!! url('/event/create') !!}" class="btn btn-success">
                <i class="glyphicon glyphicon-plus"></i> New </a>
        </p>
    </div>

    <div class="col-sm-12">
        <table class="table table-hover table-bordered table-responsive">
            <thead>
            <tr>
                <th>#</th>
                <th>Event Info</th>
                <th>Dates</th>
                <th>Online</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            @if(count($list) > 0)

                @foreach($list as $row)
                <tr>
                    <td>{!! $row->id !!}</td>
                    <td>
                        <a href="{!! url('/event/'.$row->id.'/edit')!!}"> {!! $row->name !!}</a> <br/>
                        Code : <span class="text-muted">{!! $row->code !!}</span>
                        (<span class="text-danger"> {!! $row->pages->count() !!} </span> pages)
                    </td>
                    <td>{!! date("d F Y", strtotime($row->starts)) !!} - {!! date("d F Y", strtotime($row->ends)) !!}</td>
                    <td>
                        @if($row->online == 1)
                            <a title="Switch to Offline" href="{!! url('event/toggle/'.$row->id) !!}" class="btn btn-sm btn-success">
                                <i class="glyphicon glyphicon-flash"></i>
                            </a>
                            @else
                            <a title="Switch to Online" href="{!! url('event/toggle/'.$row->id) !!}" class="btn btn-sm btn-default">
                                <i class="glyphicon glyphicon-flash"></i>
                            </a>
                        @endif
                    </td>
                    <td>
                        <a href="{!! url('/event/'.$row->id.'/edit') !!}" class="btn btn-default">
                            <i class="glyphicon glyphicon-edit"></i> Edit
                        </a>

                        <a href="{!! url('#') !!}" id="item-{!!  $row->id !!}" class="btn btn-danger delete">
                            <i class="glyphicon glyphicon-trash"></i>
                        </a>
                    </td>
                </tr>
                @endforeach

                @else
                <tr>
                    <td colspan="5">
                        <p class="text-muted text-center">Nothing to display :(</p>
                    </td>
                </tr>
            @endif
            </tbody>
        </table>

        {!! $list->render() !!}
    </div>

@endsection

@section('js')
    <script type="text/javascript">
        $(function(){
           $('a.btn.delete').on('click', function(e){
               e.preventDefault();
               var url = '/event/' + $(this).attr('id').replace(/item-/, ''),
                   row = $(this).closest("tr");

               //Show confirm and delete
               if(confirm('Are you sure! it will be gone permanently.')) {
                   $.ajax({
                       data : {_token : '{!! csrf_token() !!}' },
                       url: url,
                       type: 'DELETE',
                       success: function(result) {
                           // Do something with the result
                           row.fadeout(200);
                       }
                   })
               }
           });
        });
    </script>
@endsection