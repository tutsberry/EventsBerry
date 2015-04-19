@extends('app')

@section('css')
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
@endsection

@section('content')

    <div class="col-md-6">
       <h3 class="modal-title">Pages</h3>
    </div>
    <div class="col-md-6 text-right">
        <p>
            <a href="{!! url('/page/create') !!}" class="btn btn-success">
                <i class="glyphicon glyphicon-plus"></i> New </a>
        </p>
    </div>

    <div class="col-sm-12">
        <table class="table table-hover table-bordered table-responsive">
            <thead>
            <tr>
                <th width="5%">#</th>
                <th width="25%">Title</th>
                <th width="30%">For Event</th>
                <th width="10%">Icon</th>
                <th width="10%">Active</th>
                <th width="30%">Actions</th>
            </tr>
            </thead>
            <tbody>
            @if(count($list) > 0)

                @foreach($list as $row)
                <tr>
                    <td>{!! $row->id !!}</td>
                    <td>
                        <a href="{!! url('/page/'.$row->id.'/edit')!!}"> {!! $row->title !!}</a>
                    </td>
                    <td>
                        {!! $row->event->name !!}
                    </td>
                    <td><i class="fa text-info" style="font-size: 24px"> {!! $row->icon !!}</i></td>
                    <td>
                        @if($row->active == 1)
                            <a title="Switch to Deactivate" href="{!! url('page/toggle/'.$row->id) !!}" class="btn btn-sm btn-success">
                                <i class="glyphicon glyphicon-flash"></i>
                            </a>
                            @else
                            <a title="Switch to Activate" href="{!! url('page/toggle/'.$row->id) !!}" class="btn btn-sm btn-default">
                                <i class="glyphicon glyphicon-flash"></i>
                            </a>
                        @endif
                    </td>
                    <td>
                        <a href="{!! url('/page/'.$row->id.'/edit') !!}" class="btn btn-default">
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
               var url = '/page/' + $(this).attr('id').replace(/item-/, ''),
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