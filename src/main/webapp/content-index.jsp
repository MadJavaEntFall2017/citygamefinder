<div class="container">

    <form class="form-horizontal" action="searchGames" method="post"  id="city_form">

        <fieldset>

            <%-- Form Title --%>

            <legend>City Game Finder</legend>

            <%-- First Name --%>

            <div class="form-group">
                <label class="col-md-2 control-label">First Name*</label>
                <div class="col-md-5  inputGroupContainer">
                    <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="first_name" placeholder="First Name" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <%-- Last Name --%>

            <div class="form-group">
                <label class="col-md-2 control-label">Last Name*</label>
                <div class="col-md-5  inputGroupContainer">
                    <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="last_name" placeholder="Last Name" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <%-- Button --%>

            <div class="form-group">
                <label class="col-md-2 control-label"></label>
                <div class="col-md-5">
                    <button type="submit" class="btn btn-primary" >Search <span class="glyphicon glyphicon-send"></span></button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script type="text/javascript" src="validateForm.js"></script>