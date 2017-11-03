<div class="container">

    <form class="form-horizontal" action="searchGames" method="post"  id="city_form">

        <%-- Form Title --%>

        <legend>City Game Finder</legend>

        <br>

        <%-- Zip Code --%>

        <div class="form-group row">
            <label for="zipcode-input" class="col-2 col-form-label">I will be in: </label>
            <div class="col-2">
                <input class="form-control" type="number" id="zipcode-input" required
                       minlength="5" maxlength="5" placeholder="Zip Code">
            </div>
        </div>

        <%-- From Date --%>

        <div class="form-group row">
            <label for="from-date-input" class="col-2 col-form-label">From:</label>
            <div class="col-4">
                <input class="form-control" type="date" id="from-date-input" required>
            </div>
        </div>

        <%-- To Date --%>

        <div class="form-group row">
            <label for="to-date-input" class="col-2 col-form-label">To:</label>
            <div class="col-4">
                <input class="form-control" type="date" id="to-date-input" required>
            </div>
        </div>

        <%-- Mile Select --%>

        <div class="form-group row">
            <label for="to-date-input" class="col-2 col-form-label">Find Games Within (miles):</label>
            <div class="col-2">
                <select class="form-control" id="mile-input">
                    <option>10</option>
                    <option>25</option>
                    <option>50</option>
                    <option>75</option>
                    <option>100</option>
                    <option>250</option>
                </select>
            </div>
        </div>

        <%-- Checkboxes --%>

        <div class="form-group row">
            <div class="col-10 offset-2">
                <div class="form-check form-check-inline">
                    <label class="form-check-label">
                        <input class="form-check-input" name="list" type="checkbox" id="checkbox-nba" value="option1"> NBA
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <label class="form-check-label">
                        <input class="form-check-input" name="list" type="checkbox" id="checkbox-nfl" value="option1"> NFL
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <label class="form-check-label">
                        <input class="form-check-input" name="list" type="checkbox" id="checkbox-nhl" value="option1"> NHL
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <label class="form-check-label">
                        <input class="form-check-input" name="list" type="checkbox" id="checkbox-mlb" value="option1"> MLB
                    </label>
                </div>
            </div>
        </div>

        <%-- Button --%>

        <div class="form-group row">
            <div class="col-4 offset-2">
                <button type="submit" class="btn btn-primary" >Search<span class="glyphicon glyphicon-search"></span></button>
            </div>
        </div>
    </form>


    <script>
        function onSubmit()
        {
            var fields = $("input[name='list']").serializeArray();
            if (fields.length === 0)
            {
                alert('nothing selected');
                return false;
            }
            else
            {
                alert(fields.length + " items selected");
            }
        }
        $('#city_form').submit(onSubmit)
    </script>

</div>
